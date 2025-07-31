package com.hiscope.verified_doctor.config;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class SvgQrGenerator {

    public static String generateSvg(String content, int width, int height) {
        try {
            BitMatrix matrix = new MultiFormatWriter()
                    .encode(content, BarcodeFormat.QR_CODE, width, height);

            int matrixWidth = matrix.getWidth();
            int matrixHeight = matrix.getHeight();

            StringBuilder svg = new StringBuilder();
            svg.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            svg.append("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"").append(matrixWidth)
                    .append("\" height=\"").append(matrixHeight).append("\" shape-rendering=\"crispEdges\">\n");
            svg.append("<rect width=\"100%\" height=\"100%\" fill=\"white\"/>\n");

            for (int y = 0; y < matrixHeight; y++) {
                for (int x = 0; x < matrixWidth; x++) {
                    if (matrix.get(x, y)) {
                        svg.append("<rect x=\"").append(x)
                                .append("\" y=\"").append(y)
                                .append("\" width=\"1\" height=\"1\" fill=\"black\"/>\n");
                    }
                }
            }

            svg.append("</svg>");
            return svg.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate SVG QR", e);
        }
    }
}
