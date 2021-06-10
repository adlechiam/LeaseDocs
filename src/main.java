
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.json.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {
        PDDocument doc = new PDDocument(new COSDocument());
        JSONObject input = new JSONObject();
        input.put("t_fname", "Adle");
        input.put("t_lname", "Chiam");
        input.put("o_fname", "John");
        input.put("o_lname", "Doe");
        System.out.println(input);
        fillPDF(doc, input);
    }

    public static void fillPDF(PDDocument doc, JSONObject input) throws IOException {
        try {
            PDDocument pDDocument = PDDocument.load(new File("/Users/adlechiam/Desktop/ld/lease.pdf"));
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();

            List<PDField> fields = pDAcroForm.getFields();
            for (int i = 0; i < fields.size(); i++){
                System.out.println(fields.get(i).getFullyQualifiedName());
            }

            PDField field = pDAcroForm.getField("owner1_lname");
            field.setValue(input.getString("o_lname"));

            field = pDAcroForm.getField("owner1_fname");
            field.setValue(input.getString("o_fname"));

            field = pDAcroForm.getField("tenant1_lname");
            field.setValue(input.getString("t_lname"));

            field = pDAcroForm.getField("tenant1_fname");
            field.setValue(input.getString("t_lname"));

            pDDocument.save("/Users/adlechiam/Desktop/ld/lease-output.pdf");
            pDDocument.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
