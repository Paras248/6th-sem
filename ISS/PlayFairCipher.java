
import java.util.*;

public class PlayFairCipher {
    private static String getModifiedString(String str, ArrayList<Integer> pos){
        ArrayList<Character> strArr = new ArrayList<Character>();
        String modString = "";
        int len = str.length();

        for(int i = 0; i < len - 1; i++){
            if(str.charAt(i) == str.charAt(i + 1)){
                strArr.add(str.charAt(i));
                strArr.add('x');
                pos.add(i + 1);
                strArr.add('x');
                i++;
                strArr.add(str.charAt(i));
            } else {
                strArr.add(str.charAt(i++));
                strArr.add(str.charAt(i));
                if(i == len - 2){
                    strArr.add(str.charAt(len - 1));
                }                
            }
        }

        for(int i = 0; i < strArr.size(); i++){
            modString += strArr.get(i);
        }
        return modString;
    }
    private static void initializeHashMap(HashMap<Character, int[]> hm, char[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                hm.put(arr[i][j], new int[]{i, j});
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String str = sc.nextLine();
        boolean isOdd = false;
        ArrayList<Integer> posOfX = new ArrayList<Integer>(); 
        if(str.length() % 2 != 0){
            str += 'z';
            isOdd = true;
        }
        
        String plainText = getModifiedString(str, posOfX);
        int plainTextLen = plainText.length();
        


        char[][] playFairTable = {
            {'m', 'o', 'n', 'a', 'r'},
            {'c', 'h', 'y', 'b', 'd'},
            {'e', 'f', 'h', 'i', 'k'},
            {'l', 'p', 'q', 's', 't'},
            {'u', 'v', 'w', 'x', 'z'}  
        };
        HashMap<Character, int[]> hm = new HashMap<Character, int[]>();
        initializeHashMap(hm, playFairTable);
        
        String encString = "";
        
        for(int i = 0; i < plainTextLen; i+=2){
            int j = i;
            
            int[] pos1 = hm.get(plainText.charAt(j++));
            int[] pos2 = hm.get(plainText.charAt(j));
            int row = pos1[0], col = pos2[1]; 
            encString += playFairTable[row][col];
            
            pos1 = hm.get(plainText.charAt(j--));
            pos2 = hm.get(plainText.charAt(j));
            row = pos1[0]; col = pos2[1];
            encString += playFairTable[row][col];
        }
        System.out.println("Encrypted string: " + encString);
        
        str = "";
        
        for(int i = 0; i < encString.length(); i+=2){
            int j = i;
            int[] pos1 = hm.get(encString.charAt(j++));
            int[] pos2 = hm.get(encString.charAt(j));
            int row = pos1[0], col = pos2[1]; 
            str += playFairTable[row][col];
            
            pos1 = hm.get(encString.charAt(j--));
            pos2 = hm.get(encString.charAt(j));
            row = pos1[0]; col = pos2[1];
            str += playFairTable[row][col];
        }

        if(isOdd){
            str = str.substring(0,(str.length() - 1));
        }
        System.out.println("Decrypted String: " + str);
    }
}