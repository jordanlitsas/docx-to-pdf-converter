package converter;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.*;
import java.util.Scanner;


public class Main {




    public static void main(String[] args) throws IOException {

        boolean beginConversion = false;
        while (!beginConversion){
            Scanner runCommandScanner = new Scanner(System.in);
            String runCommand = runCommandScanner.nextLine();
            if (runCommand.equals("run")){
                beginConversion = true;
                //put all filenames in inputFile into array list
                String[] fileNames = new File("src/main/input").list();


                //Removes .docx from file name, and converts it to pdf.
                int counter;
                for (counter = 0; counter < fileNames.length; counter++){
                    try (
                            InputStream is = new FileInputStream(new File("src/main/input/" + fileNames[counter]));
                            OutputStream out = new FileOutputStream(new File("src/main/output/"+fileNames[counter].substring(0, fileNames[counter].length()-5)+".pdf"));
                    ){

                        long start = System.currentTimeMillis();
                        // 1) Load DOCX into XWPFDocument
                        XWPFDocument document = new XWPFDocument(is);
                        // 2) Prepare Pdf options
                        PdfOptions options = PdfOptions.create();
                        // 3) Convert XWPFDocument to Pdf
                        PdfConverter.getInstance().convert(document, out, options);
                        System.out.println("DOCX was converted to a PDF file in :: "
                                + (System.currentTimeMillis() - start) + " milli seconds");
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }
//        System.out.println("hello world");
        }



    }


}
