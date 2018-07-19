package codeu.controller;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class keywords {
  //method breaks string into string array and then filters out stop words.
  public static ArrayList<String> str2kw(String bio) {
      String[] arr = bio.split("[^a-zA-Z]+");
      StopWords stop  = new StopWords();
      ArrayList<String> stopwords = stop.getList();
      ArrayList<String> keywords = new ArrayList<String>();

      for (int i = 0; i < arr.length; i++) {
          if(stopwords.contains(arr[i])) {
              keywords.add(arr[i]);
          }
      }
      return keywords;
  }
  //kwcmp compares the keywords between two string arraylists, and returns the number of keywords shared by the first arraylist with the second arraylist.
  public static int kwcmp(ArrayList<String> strl1, ArrayList<String> strl2) {
      int count = 0;
      if (strl1.size() == 0 || strl2.size() == 0) { //one of the arraylists is empty for the user.
          return 0;
      }
      for (int i = 0; i < strl1.size(); i++) {
          if(strl2.contains(strl1.get(i))) {
              count++;
          }
      }
      return count;
  }
}
