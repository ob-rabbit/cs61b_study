import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    private static final double ROOT_ULLAT = 37.892195547244356, ROOT_ULLON = -122.2998046875,
            ROOT_LRLAT = 37.82280243352756, ROOT_LRLON = -122.2119140625;

    public static final int TILE_SIZE = 256;

    private static Double[] LonDPP = new Double[8];

    static {
        LonDPP[0] = (ROOT_LRLON - ROOT_ULLON) / TILE_SIZE;
        for (int i = 1; i < 8; i++) {
            LonDPP[i] = LonDPP[i - 1] / 2;
        }
    }

    public Rasterer() {

    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     * <p>
     * The grid of images must obey the following properties, where image in the
     * is referred to as a "tile".
     * <ul>
     *     <li>The tiles collected must cover the most longitudinal distance per pixel
     *     (LonDPP) possible, while still covering less than or equal to the amount of
     *     longitudinal distance per pixel in the query box for the user viewport size. </li>
     *     <li>Contains all tiles that intersect the query bounding box that fulfill the
     *     above condition.</li>
     *     <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     * </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     * forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        System.out.println(params.toString());

        Map<String, Object> results = new HashMap<>();
        Double lrlon = params.get("lrlon");
        Double lrlat = params.get("lrlat");
        Double ullon = params.get("ullon");
        Double ullat = params.get("ullat");
        Double width = params.get("w");
        //计算每像素的经度
        Double reqLonDPP = (lrlon - ullon) / width;
        int depth = calDepth(reqLonDPP);

        Double sideLength = Math.pow(2, depth);
        results.put("depth", depth);

        Double perPicX = (ROOT_LRLON - ROOT_ULLON) / sideLength;
        //负数
        Double perPicY = (ROOT_LRLAT - ROOT_ULLAT) / sideLength;

        int upLeftX = 0, upLeftY = 0, lowRightX = 0, lowRightY = 0;
        //定位包含请求的图片名x,y
        for (double x = ROOT_ULLON; x <= ROOT_LRLON; x += perPicX) {
            if (x <= ullon) {
                upLeftX++;
            }
            if (lowRightX < sideLength && x <= lrlon) {
                lowRightX++;
            }
        }
        for (double y = ROOT_ULLAT; y >= ROOT_LRLAT; y += perPicY) {
            if (y >= ullat) {
                upLeftY++;
            }
            if (lowRightY < sideLength && y >= lrlat) {
                lowRightY++;
            }
        }
        if (upLeftX != 0) {
            upLeftX--;
        }
        if (upLeftY != 0) {
            upLeftY--;
        }
        if (lowRightX != 0) {
            lowRightX--;
        }
        if (lowRightY != 0) {
            lowRightY--;
        }

        //System.out.println("upLeftX="+upLeftX+" upLeftY="+upLeftY+" lowRightX="+lowRightX+" lowRightY="+lowRightY);
        //存放图片名称
        String[][] picFileName = new String[lowRightY - upLeftY + 1][lowRightX - upLeftX + 1];

        for (int y = upLeftY; y <= lowRightY; y++) {
            for (int x = upLeftX; x <= lowRightX; x++) {
                picFileName[y - upLeftY][x - upLeftX] = "d" + depth + "_x" + x + "_y" + y + ".png";
            }
        }

        results.put("render_grid", picFileName);
        results.put("raster_ul_lon", ROOT_ULLON + upLeftX * perPicX);
        results.put("raster_ul_lat", ROOT_ULLAT + upLeftY * perPicY);
        results.put("raster_lr_lon", ROOT_ULLON + (lowRightX + 1) * perPicX);
        results.put("raster_lr_lat", ROOT_ULLAT + (lowRightY + 1) * perPicY);
        results.put("query_success", true);
        System.out.println(results.toString());
        /*System.out.print("[");
        for (String[] strings : picFileName) {
            System.out.print("[");
            for (String string : strings) {
                System.out.print(string + ",");
            }
            System.out.println("],");
        }
        System.out.println("]");*/
        return results;
    }

    private int calDepth(Double reqLonDPP) {
        int depth = 0;
        for (; depth < 7; depth++) {
            if (reqLonDPP >= LonDPP[depth]) {
                break;
            }
        }
        return depth;
    }

}
