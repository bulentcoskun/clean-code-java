package victor.training.refactoring.large.sample1;

public class Play {
   public static void main(String[] args) {
//      if (A)
//         else if (B)
//            else{ stuff
//      }
//      }

      switch (args.length) {
         case 1: {
            int x = 1;
            System.out.println(x);
            break;
         }
         case 2: {
            int x = 2;

            System.out.println("x " + x);
            break;
         }      }

   }
}