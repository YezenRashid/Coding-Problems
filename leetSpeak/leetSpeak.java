public static void leetSpeak(Scanner input, PrintStream output) {
    while (input.hasNextLine()) {
        String line = input.nextLine();
        Scanner lineScanner = new Scanner(line);
        while (lineScanner.hasNext()) {
            String word = lineScanner.next();
            word = word.replace("o", "0");
            word = word.replace("l", "1");
            word = word.replace("e", "3");
            word = word.replace("a", "4");
            word = word.replace("t", "7");
            if (word.endsWith("s")) {
                word = word.substring(0, word.length() - 1) + "Z";
            }
            output.print("(" + word + ") ");
        }
        output.println();
    }
}