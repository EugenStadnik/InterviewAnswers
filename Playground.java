import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Playground {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.setName("myThread");
        myThread.start();
        System.out.println("Sleeping in " + Thread.currentThread().getName());
        try {
            Thread.currentThread().sleep(2000);
            System.out.println("UNSleeping in " + Thread.currentThread().getName());
            myThread.interrupt();
        } catch (InterruptedException ie) {
            System.out.println(myThread.getName() + " thread is interrupted outside.");
        }
        String[] names = {"Alina", "Yevhen", "Maria", "Oleh"};
        System.out.println(deleteFirstCharacterAndSort(Arrays.asList(names)));
        System.out.println(countCharactersInWord("a baba galamaga"));
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; 2>1; i++) {
                try {
                    Thread.currentThread().sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " thread is interrupted inside run().");
                    break;
                }
                System.out.println("" + i + " in " + Thread.currentThread().getName());
            }
        }
    }

    public static Map<Character, Integer> countCharactersInWord(String word) {
        Map<Character, Integer> result = new HashMap<>();
        word.chars().map((charCode) -> {
            result.putIfAbsent((char)charCode, 0);
            return (char)charCode;
        }).forEach((charCode) -> {
            result.put((char)charCode, result.get((char)charCode) + 1);
        });
        return result;
    }

    public static List<String> deleteFirstCharacterAndSort(List<String> words) {
        return words.stream().map(name -> name.replace("" + name.charAt(0), "")).sorted().collect(Collectors.toList());
    }

    public static List<String> sortAndDeleteFirstCharacter(List<String> words) {
        return words.stream().sorted().map(name -> name.replace("" + name.charAt(0), "")).collect(Collectors.toList());
    }
}
