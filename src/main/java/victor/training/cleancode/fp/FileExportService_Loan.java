package victor.training.cleancode.fp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Unchecked;
import victor.training.cleancode.fp.support.OrderRepo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@RequiredArgsConstructor
public class FileExportService_Loan {
   private final OrderRepo orderRepo;

   public void exportOrders() throws IOException {
      File file = new File("target/orders.csv");
      System.out.println("Starting export into {} ... " + file.getAbsolutePath());
      long t0 = System.currentTimeMillis();
      try (Writer writer = new FileWriter(file)) {

         writer.write("order_id;date\n");
         orderRepo.findByActiveTrue()
             .map(o -> o.getId() + ";" + o.getCreationDate() + "\n")
             .forEach(Unchecked.consumer(writer::write));

         System.out.println("Export DONE");
      } catch (Exception e) {
         System.out.println("Export FAILED: " + e); // TERROR-Driven Development
         // imagine... sendErrorEmail(e);
         throw e;
      } finally {
         long t1 = System.currentTimeMillis();
         System.out.println("Export completed in seconds: " + (t1 - t0) / 1000);
      }
   }
}

