import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Code128BarcodeGenerator {

   public static void generateBarcode(String input, String outputPath) {
       try {
           Code128Bean barcodeBean = new Code128Bean();
           barcodeBean.setModuleWidth(0.3);
           barcodeBean.setHeight(15.0);
           barcodeBean.doQuietZone(true);

           BitmapCanvasProvider canvas = new BitmapCanvasProvider(
               300, BufferedImage.TYPE_INT_ARGB, true, 0);

           barcodeBean.generateBarcode(canvas, input);
           BufferedImage barcodeImage = canvas.getBufferedImage();

           File outputFile = new File(outputPath);
           javax.imageio.ImageIO.write(barcodeImage, "PNG", outputFile);

           System.out.println("Barcode generated and saved to: " + outputPath);
       } catch (IOException e) {
           System.err.println("Error generating barcode: " + e.getMessage());
       }
   }

   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);

       System.out.print("Enter the string for the barcode: ");
       String input = scanner.nextLine();

       if (input == null || input.trim().isEmpty()) {
           System.err.println("Error: Input cannot be empty.");
           scanner.close();
           return;
       }

       System.out.print("Enter the output file path (e.g., barcode.png): ");
       String outputPath = scanner.nextLine();

       if (outputPath == null || outputPath.trim().isEmpty() || !outputPath.toLowerCase().endsWith(".png")) {
           System.err.println("Error: Please provide a valid PNG file path (e.g., barcode.png).");
           scanner.close();
           return;
       }

       generateBarcode(input, outputPath);
       scanner.close();
   }
}