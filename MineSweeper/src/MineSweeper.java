import java.util.Arrays;
import java.util.Scanner;

public class MineSweeper {
    Scanner scan = new Scanner(System.in);
    int row;
    int col;
    int predictRow;
    int predictCol;
    int num;
    String[][] arr;
    String[][] arrayForGamerPredcit;

    MineSweeper() {

    }

    // Değerlendirme formu 6
    public void run() {
        //Create array with number of coloumn and row
        creatDefaultArray();
        //Add array bomb
        addMine();
        //Create another array for gamer input
        createGamerArray();
        // Değerlendirme formu 15
        //Start Game
        if(playGame())
            //Print to screen if win
            System.out.println("\nOyunu Kazandınız.");
        else
            //Print to screen if lose
            System.out.println("\nMayın patladı. Oyunu kaybettiniz.");
        //Print to screen gamer array with bomb
        printGamerArrayWithBomb();
    }

    // Değerlendirme formu 7
    //Create array with number of column and row
    private void creatDefaultArray() {
        do {
            //User enter number of row for matris
            Scanner scan = new Scanner(System.in);
            System.out.print("Matrisin satır sayısı için değer giriniz: ");
            row = scan.nextInt();
            //number of row must be greater then 1
            if(this.row<2 )
                System.out.println(">>Gireceğiniz değerler 1'den büyük olmalı.");
        }while (this.row<2 );

        do {
            //User enter number of row for matris
            System.out.print("Matrisin kolon sayısı için değer giriniz: ");
            col = scan.nextInt();
            //number of column must be greater then 1
            if( this.col<2)
                System.out.println(">>Gireceğiniz değerler 1'den büyük olmalı.");
        }while (this.col<2);

        //Create default array
        this.arr = new String[this.row][this.col];
        //Create gamer array
        this.arrayForGamerPredcit = new String[this.row][this.col];

        //fill in default array  with "-"
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                this.arr[i][j] = "-";
//             System.out.print("-");
            }
//         System.out.println();
        }
    }

    // Değerlendirme formu 8
    //add mine to default array
    public void addMine() {
        //calculate number of mine
        int numOfMine = this.row * this.col / 4;
        int a = 1;
        //Add all of mine to matris
        while (0 < numOfMine) {
            //randomly add mine  to matris
            int random1 = (int) (Math.random() * this.row);
            int random2 = (int) (Math.random() * this.col);
            //check if you doesn't add mine to same place
            if (this.row != random1 && this.col != random2) {
                if (!this.arr[random1][random2].equals("*")) {
                    arr[random1][random2] = "*";
//                    arrayForBombLocation = new int[1][a];
                    numOfMine--;
                    a++;
                }
            }
//            arrayForGamerPredcit = Arrays.copyOf(arr,arr.length);
        }
        //print location of mines
        printMineLocation();

    }

    //print location of mines
    public void printMineLocation() {
        System.out.println("====Mine Matris===");
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    //create array for gamer
    public void createGamerArray() {
        System.out.println("====Gamer Matris===");
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                //firslt, fill in gamerArray with "-"
                this.arrayForGamerPredcit[i][j] = "-";
                System.out.print(this.arrayForGamerPredcit[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    //start game
    public boolean playGame() {
        int number = 0;
        // Değerlendirme formu 9
        while (number == 0) {
            do {
                //user predict for row
                System.out.print("Mayın olmadığını düşündüğünüz satır tahmininizi giriniz: ");
                this.predictRow = scan.nextInt();
                // Değerlendirme formu 10
                //user's predict must be between 0 and row-1
                if(0>predictRow || predictRow>=row )
                    System.out.println(">>Gireceğiniz değer 0 ile " + (this.row -1)  + " arasında olmalı.");

            }while (0>predictRow || predictRow>=row );

            do {
                //user predict for column
                System.out.print("Mayın olmadığını düşündüğünüz sütun tahmininizi giriniz: ");
                this.predictCol = scan.nextInt();
                // Değerlendirme formu 10
                //user's predict must be between 0 and column-1
                if (0 > col || predictCol >= col)
                    System.out.println(">>Gireceğiniz değer 0 ile " + (this.col - 1) + " arasında olmalı.");
                }while (0 > predictCol || predictCol >= col) ;

               //print location of mine
                printMineLocation();
                // Değerlendirme formu 12
                //check if bomb exsit in user's predict location
                if (checkBomb(predictRow, predictCol)) {
                    arrayForGamerPredcit[predictRow][predictCol] = String.valueOf(countBombNumber(predictRow, predictCol));
                }
                //check game's situation; 0 = continue, 1 = win, 2 = lose
                number = checkGamerWinGameOrLoseGameOrContinueGame();
                // Değerlendirme formu 11
                //print gamer matris
                printGamerArray();
        }
        //check game's situation;  1 = win, 2 = lose
        if(number == 1)
            return true;
        else
            return false;
    }

    //print gamer matris
    public   void printGamerArray() {
        System.out.println("====Gamer Array====");
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {

                System.out.print(arrayForGamerPredcit[i][j]);
            }
            System.out.println();
        }
    }

    //calcuate number of mine around chosen location
    public int countBombNumber(int x, int y) {
        int count=0;
        for (int i=x-1;i<x+2;i++){
            for (int j=y-1;j<y+2;j++){
                if(i<arr.length && 0<=i && 0<=j && j<arr[0].length ){
                    if(arr[i][j].equals("*"))
                        count++;
                }
            }
        }
        return count;
    }
    // Değerlendirme formu 13
    // Değerlendirme formu 14
    //check game's situation; 0 = continue, 1 = win, 2 = lose
    public int checkGamerWinGameOrLoseGameOrContinueGame() {
        if(checkBomb(predictRow, predictCol)){
            for (int i=0;i<arrayForGamerPredcit.length;i++){
                for (int j=0;j<arrayForGamerPredcit[0].length;j++){
                    if(!arr[i][j].equals("*")){
                        if(arrayForGamerPredcit[i][j].equals("-")){
                            return 0;
                        }
                    }
                }
            }
            return 1;
        }else {
            return 2;
        }
    }

    //check if mine exists
    public boolean checkBomb(int predictRow, int predictCol) {
        if (arr[predictRow][predictCol].equals("*")) {
            return false;
        }
        return true;
    }

    //print gamer array with mines
    public void printGamerArrayWithBomb() {
        System.out.println("====Gamer Matris With Bomb===");
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                if (arr[i][j].equals("*")){
                    this.arrayForGamerPredcit[i][j] = "*";
                }
                System.out.print(this.arrayForGamerPredcit[i][j]);
            }
            System.out.println();
        }
    }
}

