import java.util.Scanner;

public class Main {
   static double[] Curry = {25.5, 5.5, 4.5};
   static double[] Lebron = {23.5, 7.5, 7};
   static double[] Giannis = {25.5, 8, 4.5};
   static double[] Durant = {26.5, 7.5, 3.5};
   static double[] Doncic = {28.5, 6.5, 6.5};

   static double[] Curry_Avg = {22.5, 5.5, 6.3};
   static double[] Lebron_Avg = {22, 8, 9.1};
   static double[] Giannis_Avg = {32.9, 11.9, 6.6};
   static double[] Durant_Avg = {26.8, 6.9, 3.2};
   static double[] Doncic_Avg = {28.6, 7.6, 8.0};

   public static void main(String[] args) {
       Scanner in = new Scanner(System.in);

       System.out.println("Hi! I'm WagerWhiz. Would you like to see today's list? (Reply Yes or No)");
       String userResp = in.nextLine();

       if (userResp.equalsIgnoreCase("Yes")) {
           System.out.println("Player Props List:");
           printPlayerStats("Curry", Curry);
           printPlayerStats("Lebron", Lebron);
           printPlayerStats("Giannis", Giannis);
           printPlayerStats("Durant", Durant);
           printPlayerStats("Doncic", Doncic);
       } else if (userResp.equalsIgnoreCase("No")) {
           System.out.println("Alright, have a great day!");
           return;
       } else {
           System.out.println("Invalid response. Please reply with Yes or No.");
           return;
       }

       System.out.println("Who do you want to bet on?");
       String resp = in.nextLine();

       while (!isValidPlayer(resp)) {
           System.out.println("Sorry, I am unaware of that person. Please choose from this list: Curry, LeBron, Giannis, Doncic, Durant.");
           System.out.println("Who do you want to bet on?");
           resp = in.nextLine();
       }

       double[] stats = getPlayerStats(resp);
       double[] averages = getPlayerAverages(resp);

       if (stats != null && averages != null) {
           printPlayerStats(resp, stats);
           System.out.println("What category do you want to bet on? (Points, Rebounds, Assists)");
           String category = in.nextLine().toLowerCase();

           int index = getCategoryIndex(category);
           if (index != -1) {
               compareWithAverages(resp, stats[index], averages[index]);
           } else {
               System.out.println("Invalid category. Please choose from Points, Rebounds, or Assists.");
           }
       }
   }

   private static boolean isValidPlayer(String player) {
       return player.equalsIgnoreCase("Curry") || 
              player.equalsIgnoreCase("Lebron") || 
              player.equalsIgnoreCase("Giannis") || 
              player.equalsIgnoreCase("Durant") || 
              player.equalsIgnoreCase("Doncic");
   }

   private static double[] getPlayerStats(String player) {
       switch (player.toLowerCase()) {
           case "curry": return Curry;
           case "lebron": return Lebron;
           case "giannis": return Giannis;
           case "durant": return Durant;
           case "doncic": return Doncic;
           default: return null;
       }
   }

   private static double[] getPlayerAverages(String player) {
       switch (player.toLowerCase()) {
           case "curry": return Curry_Avg;
           case "lebron": return Lebron_Avg;
           case "giannis": return Giannis_Avg;
           case "durant": return Durant_Avg;
           case "doncic": return Doncic_Avg;
           default: return null;
       }
   }

   private static void printPlayerStats(String name, double[] stats) {
       System.out.printf("%s Props O/U Points: %.1f Rebounds: %.1f Assists: %.1f%n",
                         name, stats[0], stats[1], stats[2]);
   }

   private static int getCategoryIndex(String category) {
       switch (category) {
           case "points": return 0;
           case "rebounds": return 1;
           case "assists": return 2;
           default: return -1;
       }
   }

   private static void compareWithAverages(String player, double propStat, double avgStat) {
       if (avgStat < propStat) {
           System.out.println("I would take the over for " + player + "'s " + getCategoryName(propStat) + " prop.");
       } else if (avgStat > propStat) {
           System.out.println("I would take the under for " + player + "'s " + getCategoryName(propStat) + " prop.");
       } else {
           System.out.println("It is up to you for " + player + "'s " + getCategoryName(propStat) + " prop.");
       }
   }

   private static String getCategoryName(double stat) {
       if (stat == Curry[0] || stat == Lebron[0] || stat == Giannis[0] || stat == Durant[0] || stat == Doncic[0]) {
           return "Points";
       } else if (stat == Curry[1] || stat == Lebron[1] || stat == Giannis[1] || stat == Durant[1] || stat == Doncic[1]) {
           return "Rebounds";
       } else {
           return "Assists";
       }
   }
}
