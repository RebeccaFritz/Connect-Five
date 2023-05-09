import java.util.Random;

class FritzFP implements FinalProject{

    // Private Variables
        private Random random = new Random();
        private char computerPiece;
        private char opponentPiece;
        private char[][] board;
        private int[] freeSpace = new int[2];   // an array holding the row and column idx of a free space where the next peice should be placed
                                                // side effects will be used to update freeSpace when private methods are running

    // Public Methods
        public int[] playShortGame(char[][] b, int player){
            int[] myMove = new int[2];
            this.board = b;
        
            if(player == 1){
                this.computerPiece = 'X';
                this.opponentPiece = 'O';
            } else if(player == 2){
                this.computerPiece = 'O';
                this.opponentPiece = 'X';
            }
            
            if(playerOneOpen() || playerTwoOpen()){
                myMove = this.freeSpace;
                return myMove;
            } else if(computerCanWin()){
                // check if the computer has four in a row
                // if so, take win
                myMove = this.freeSpace;
                return myMove;
            } else if(opponentCanWin()){
                // block win
                // if the player has four uninterupted pieces, only block if one side is already blocked 
                // if the pieces are interrupted block within the interuption
                myMove = this.freeSpace;
                return myMove;
            } else if(computerCanMakeFourUnblockedInARow()){
                // check if the computer has can make four unblocked in a row
                // if so, make four
                myMove = this.freeSpace;
                return myMove;
            } else if(hasThreeInFive(this.opponentPiece)){ 
                // check if the opponent has three in a set of five
                // if unblocked, block three
                myMove = this.freeSpace;
                return myMove;
            } else if(hasIntersectingTwos(this.computerPiece)){
                /* checks scenarios as such:
                 * -O-O-
                 * -OO--
                 * -----
                 */
                // create intersection
                myMove = this.freeSpace;
                return myMove;
            } else if(hasIntersectingTwos(this.opponentPiece)){
                /* checks scenarios as such:
                 * -O-O-
                 * -OO--
                 * -----
                 */
                // block intersection
                myMove = this.freeSpace;
                return myMove;
            } else if(hasThreeInFive(this.computerPiece)){
                // check if the computer has three in a set of five
                // make four
                myMove = this.freeSpace;
                return myMove;
            } else if(computerHasTwo()){
                // check if the computer has two in a set of five
                // make three
                myMove = this.freeSpace;
                return myMove;
            } else if(computerHasOne()){
                // check if the computer has one piece in a set of five
                // make two
                myMove = this.freeSpace;
                return myMove;
            } else {
                // if there is an empty five by five square, place a piece in the middle
                for(int i = 2; i < 18; i++){
                    for(int j = 2; j < 18; j++){
                        if(isEmptyFivebyFive(i, j)){
                            myMove[0] = i;
                            myMove[1] = j;
                            return myMove;
                        }
                    }
                }

                // if there is a space with 4 blank spaces around it then choose that space 
                for(int i = 0; i < 20; i++){
                    for(int j = 0; j < 20; j++){
                        if(isEmptySetOfFive(i, j)){
                            myMove[0] = i;
                            myMove[1] = j;
                            return myMove;
                        }
                    }
                }
                
                // otherwise choose a random empty space
                int randomRow = this.random.nextInt(20);
                int randomColumn = this.random.nextInt(20);
                while(this.board[randomRow][randomColumn] != '.'){
                    randomRow = this.random.nextInt(20);
                    randomColumn = this.random.nextInt(20);
                }   
                myMove[0] = randomRow;
                myMove[1] = randomColumn;
                return myMove;
            }
        }
        
        public int[] playLongGame(char[][] b, int player) {
            int[] myMove = new int[2];
            this.board = b;
        
            if(player == 1){
                this.computerPiece = 'X';
                this.opponentPiece = 'O';
            } else if(player == 2){
                this.computerPiece = 'O';
                this.opponentPiece = 'X';
            }
        
            if(playerOneOpen() || playerTwoOpen()){
                myMove = this.freeSpace;
                return myMove;
            } else if(computerCanWinLong()){
                // check if the computer has 4, 9, 14, or 19 
                // with a free space on at least one side
                // if so, make 5, 10, 15, or 20 (respectively)
                myMove = this.freeSpace;
                return myMove;
            } else if(opponentCanWinLong()){
                // block win
                // if the opponent has 4, 9, 14, or 19 uninterupted pieces
                // only block if one side is already blocked 
                // if the pieces are interrupted block within the interuption
                myMove = this.freeSpace;
                return myMove;
            } else if(computerCanMakeFourUnblockedInARow() || computerCanMakeNineUnblockedInARow() || computerCanMakeFourteenUnblockedInARow()){
                // check if the computer has 3/8/13 in a row
                // if neither side is blocked, make 4/9/14 (this scenario gurantees that the computer will get 5/10/15)
                myMove = this.freeSpace;
                return myMove;
            } else if(hasThreeInFive(this.opponentPiece) || hasEightInTen(this.opponentPiece) || hasThirteenInFifteen(this.opponentPiece)){
                // check if the opponent has unblocked three in a set of five
                // if so, block three
                myMove = this.freeSpace;
                return myMove;
            } else if(hasIntersectingSevens(this.computerPiece) || hasIntersectingTwos(this.computerPiece)){
                /* checks scenarios as such:
                 * -O-O-
                 * -OO--
                 * -----
                 */
                // make intersection
                myMove = this.freeSpace;
                return myMove;
            } else if(hasIntersectingSevens(this.opponentPiece) || hasIntersectingTwos(this.opponentPiece)){
                /* checks scenarios as such:
                 * -O-O-
                 * -OO--
                 * -----
                 */
                // block intersection
                myMove = this.freeSpace;
                return myMove;
            } else if(hasThreeInFive(this.computerPiece) || hasEightInTen(this.computerPiece) || hasThirteenInFifteen(this.computerPiece) || hasEighteenInTwenty(this.computerPiece)){
                // check if the computer has 3/8/13 in a set of 5/10/15
                // make 4/9/14 in a set of 5/10/15
                myMove = this.freeSpace;
                return myMove;
            } else if(computerHasTwo()){ // need to make computerHasSeven(), computerHasTwelve(), and computerHasSeventeen() methods
                // check if the computer has two in a set of five
                // make three
                myMove = this.freeSpace;
                return myMove;
            } else if(computerHasOne()){ // need to make computerHasSix(), computerHasEleven(), and computerHasSixteen() methods
                // check if the computer has one piece in a set of five
                // make two
                myMove = this.freeSpace;
                return myMove;
            } else {
                // if there is an empty five by five square, place a piece in the middle
                for(int i = 2; i < 18; i++){
                    for(int j = 2; j < 18; j++){
                        if(isEmptyFivebyFive(i, j)){
                            myMove[0] = i;
                            myMove[1] = j;
                            return myMove;
                        }
                    }
                }
                // if there is a space with 4 blank spaces around it then choose that space 
                for(int i = 0; i < 20; i++){
                    for(int j = 0; j < 20; j++){
                        if(isEmptySetOfFive(i, j)){
                            myMove[0] = i;
                            myMove[1] = j;
                            return myMove;
                        }
                    }
                }
                
                // otherwise choose a random empty space
                int randomRow = this.random.nextInt(20);
                int randomColumn = this.random.nextInt(20);
                while(this.board[randomRow][randomColumn] != '.'){
                    randomRow = this.random.nextInt(20);
                    randomColumn = this.random.nextInt(20);
                }   
                myMove[0] = randomRow;
                myMove[1] = randomColumn;
                return myMove;
            }
        }

        public int isShortGameOver(char[][] b){
            this.board = b;

            if(findRowOfN(5, 'X') || findColumnOfN(5, 'X') || findDiagonalDownOfN(5, 'X') || findDiagonalUpOfN(5, 'X')){ // check if the computer won
                return 1;
            } else if(findRowOfN(5, 'O') || findColumnOfN(5, 'O') || findDiagonalDownOfN(5, 'O') || findDiagonalUpOfN(5, 'O')){ // check if the opponent won
                return 2;
            } else if(fullBoard()){ // check if there are no moves left
                return 0;
            } else { 
                return 0;
            }  
        }

        public int isLongGameOver(char[][] b){
            this.board = b;
            if(fullBoard()){
                return determineLongWinner();
            } else {
                return 0;
            }
        }
        
    // Private Methods

    private boolean playerOneOpen(){
        if(boardHasNPieces(0)){
            int[] possibleIdx = {4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
            this.freeSpace[0] = possibleIdx[random.nextInt(12)];
            this.freeSpace[1] = possibleIdx[random.nextInt(12)];
            return true;
        } else {
            return false;
        }
    }

    private boolean playerTwoOpen(){
        int firstRowIdx = 100;
        int firstColumnIdx = 100;
        if(boardHasNPieces(1)){
            for(int i = 0; i < 20; i++){
                for(int j = 0; j < 20; j++){
                    if(this.board[i][j] == 'X'){ // find the first piece
                        firstRowIdx = i;
                        firstColumnIdx = j;
                    }
                }
            }
            if(firstRowIdx > 4 && firstColumnIdx > 4 && firstRowIdx < 15 && firstColumnIdx < 15){
                int randomValue = this.random.nextInt(4);
                if(randomValue == 0){
                    this.freeSpace[0] = firstRowIdx-1;
                    this.freeSpace[1] = firstColumnIdx-1;
                    return true;
                } else if(randomValue == 1){
                    this.freeSpace[0] = firstRowIdx+1;
                    this.freeSpace[1] = firstColumnIdx+1;
                    return true;
                } else if(randomValue == 2){
                    this.freeSpace[0] = firstRowIdx-1;
                    this.freeSpace[1] = firstColumnIdx+1;
                    return true;
                } else {
                    this.freeSpace[0] = firstRowIdx+1;
                    this.freeSpace[1] = firstColumnIdx-1;
                    return true;
                }
            } else if(firstRowIdx < 4 && firstColumnIdx < 4 && firstRowIdx == firstColumnIdx){
                this.freeSpace[0] = 4;
                this.freeSpace[1] = 4;
                return true;
            } else if(firstRowIdx < 4 && firstColumnIdx > 15 && firstRowIdx == 19-firstColumnIdx){
                this.freeSpace[0] = 4;
                this.freeSpace[1] = 15;
                return true;
            } else if(firstRowIdx > 15 && firstColumnIdx > 15 && firstRowIdx == firstColumnIdx){
                this.freeSpace[0] = 15;
                this.freeSpace[1] = 15;
                return true;
            } else if(firstRowIdx > 15 && firstColumnIdx < 4 && 19-firstRowIdx == firstColumnIdx){
                this.freeSpace[0] = 15;
                this.freeSpace[1] = 4;
                return true;
            } else if(firstRowIdx > 3 && firstRowIdx < 10 && firstColumnIdx < 4){
                this.freeSpace[0] = firstRowIdx+(4-firstColumnIdx);
                this.freeSpace[1] = 4;
                return true;
            } else if(firstRowIdx > 9 && firstRowIdx < 16 && firstColumnIdx < 4){
                this.freeSpace[0] = firstRowIdx-(4-firstColumnIdx);
                this.freeSpace[1] = 4;
                return true;
            } else if(firstRowIdx > 3 && firstRowIdx < 10 && firstColumnIdx > 15){
                this.freeSpace[0] = firstRowIdx+(4-(19-firstColumnIdx));
                this.freeSpace[1] = 15;
                return true;
            } else if(firstRowIdx > 9 && firstRowIdx < 16 && firstColumnIdx > 15){
                this.freeSpace[0] = firstRowIdx-(4-(19-firstColumnIdx));
                this.freeSpace[1] = 15;
                return true;
            } else if(firstColumnIdx > 3 && firstColumnIdx < 10 && firstRowIdx < 4){
                this.freeSpace[0] = 4;
                this.freeSpace[1] = firstColumnIdx+(4-firstRowIdx);
                return true;
            } else if(firstColumnIdx > 9 && firstColumnIdx < 16 && firstRowIdx < 4){
                this.freeSpace[0] = 4;
                this.freeSpace[1] = firstColumnIdx-(4-firstRowIdx);
                return true;
            } else if(firstColumnIdx > 3 && firstColumnIdx < 10 && firstRowIdx > 15){
                this.freeSpace[0] = 15;
                this.freeSpace[1] = firstColumnIdx+(4-(19-firstRowIdx));
                return true;
            } else if(firstColumnIdx > 9 && firstColumnIdx < 16 && firstRowIdx > 15){
                this.freeSpace[0] = 15;
                this.freeSpace[1] = firstColumnIdx-(4-(19-firstRowIdx));
                return true;
            } else if(firstRowIdx == 4 && firstColumnIdx > 4 && firstColumnIdx < 15){
                int randomValue = this.random.nextInt(2);
                if(randomValue == 0){
                    this.freeSpace[0] = 5;
                    this.freeSpace[1] = firstColumnIdx+1;
                    return true;
                } else {
                    this.freeSpace[0] = 5;
                    this.freeSpace[1] = firstColumnIdx-1;
                    return true;
                }
            } else if(firstRowIdx == 15 && firstColumnIdx > 4 && firstColumnIdx < 15){
                int randomValue = this.random.nextInt(2);
                if(randomValue == 0){
                    this.freeSpace[0] = 14;
                    this.freeSpace[1] = firstColumnIdx+1;
                    return true;
                } else {
                    this.freeSpace[0] = 14;
                    this.freeSpace[1] = firstColumnIdx-1;
                    return true;
                }
            } else if(firstColumnIdx == 4 && firstRowIdx > 4 && firstRowIdx < 15){
                int randomValue = this.random.nextInt(2);
                if(randomValue == 0){
                    this.freeSpace[0] = firstRowIdx+1;
                    this.freeSpace[1] = 5;
                    return true;
                } else {
                    this.freeSpace[0] = firstRowIdx-1;
                    this.freeSpace[1] = 5;
                    return true;
                }
            } else if(firstColumnIdx == 15 && firstRowIdx > 4 && firstRowIdx < 15){
                int randomValue = this.random.nextInt(2);
                if(randomValue == 0){
                    this.freeSpace[0] = firstRowIdx+1;
                    this.freeSpace[1] = 14;
                    return true;
                } else {
                    this.freeSpace[0] = firstRowIdx-1;
                    this.freeSpace[1] = 14;
                    return true;
                }
            } else if(firstRowIdx == 4 && firstColumnIdx == 4){
                this.freeSpace[0] = 5;
                this.freeSpace[1] = 5;
                return true;
            } else if(firstRowIdx == 4 && firstColumnIdx == 15){
                this.freeSpace[0] = 5;
                this.freeSpace[1] = 14;
                return true;
            } else if(firstRowIdx == 15 && firstColumnIdx == 15){
                this.freeSpace[0] = 14;
                this.freeSpace[1] = 14;
                return true;
            } else if(firstRowIdx == 15 && firstColumnIdx == 4){
                this.freeSpace[0] = 14;
                this.freeSpace[1] = 5;
                return true;
            } else if((firstColumnIdx == 0 && firstRowIdx == 1) || (firstColumnIdx == 1 && firstRowIdx == 2) || (firstColumnIdx == 2 && firstRowIdx == 3)){
                this.freeSpace[0] = 5;
                this.freeSpace[1] = 4;
                return true;
            } else if((firstColumnIdx == 0 && firstRowIdx == 2) || (firstColumnIdx == 1 && firstRowIdx == 3)){
                this.freeSpace[0] = 6;
                this.freeSpace[1] = 4;
                return true;
            } else if(firstColumnIdx == 0 && firstRowIdx == 3){
                this.freeSpace[0] = 7;
                this.freeSpace[1] = 4;
                return true;
            } else if((firstColumnIdx == 1 && firstRowIdx == 0) || (firstColumnIdx == 2 && firstRowIdx == 1) || (firstColumnIdx == 3 && firstRowIdx == 2)){
                this.freeSpace[0] = 4;
                this.freeSpace[1] = 5;
                return true;
            } else if((firstColumnIdx == 2 && firstRowIdx == 0) || (firstColumnIdx == 3 && firstRowIdx == 1)){
                this.freeSpace[0] = 4;
                this.freeSpace[1] = 6;
                return true;
            } else if(firstColumnIdx == 3 && firstRowIdx == 0){
                this.freeSpace[0] = 4;
                this.freeSpace[1] = 7;
                return true;
            } else if((firstColumnIdx == 18 && firstRowIdx == 0) || (firstColumnIdx == 17 && firstRowIdx == 1) || (firstColumnIdx == 16 && firstRowIdx == 2)){
                this.freeSpace[0] = 4;
                this.freeSpace[1] = 14;
                return true;
            } else if((firstColumnIdx == 17 && firstRowIdx == 0) || (firstColumnIdx == 16 && firstRowIdx == 1)){
                this.freeSpace[0] = 4;
                this.freeSpace[1] = 13;
                return true;
            } else if(firstColumnIdx == 16 && firstRowIdx == 0){
                this.freeSpace[0] = 4;
                this.freeSpace[1] = 12;
                return true;
            } else if((firstColumnIdx == 0 && firstRowIdx == 18) || (firstColumnIdx == 1 && firstRowIdx == 17) || (firstColumnIdx == 2 && firstRowIdx == 16)){
                this.freeSpace[0] = 14;
                this.freeSpace[1] = 4;
                return true;
            } else if((firstColumnIdx == 0 && firstRowIdx == 17) || (firstColumnIdx == 1 && firstRowIdx == 16)){
                this.freeSpace[0] = 13;
                this.freeSpace[1] = 4;
                return true;
            } else if(firstColumnIdx == 0 && firstRowIdx == 16){
                this.freeSpace[0] = 12;
                this.freeSpace[1] = 4;
                return true;
            } else if((firstColumnIdx == 1 && firstRowIdx == 19) || (firstColumnIdx == 2 && firstRowIdx == 18) || (firstColumnIdx == 3 && firstRowIdx == 17)){
                this.freeSpace[0] = 15;
                this.freeSpace[1] = 5;
                return true;
            } else if((firstColumnIdx == 2 && firstRowIdx == 19) || (firstColumnIdx == 3 && firstRowIdx == 18)){
                this.freeSpace[0] = 15;
                this.freeSpace[1] = 6;
                return true;
            } else if(firstColumnIdx == 3 && firstRowIdx == 19){
                this.freeSpace[0] = 15;
                this.freeSpace[1] = 7;
                return true;
            } else if((firstColumnIdx == 19 && firstRowIdx == 1) || (firstColumnIdx == 18 && firstRowIdx == 2) || (firstColumnIdx == 17 && firstRowIdx == 3)){
                this.freeSpace[0] = 5;
                this.freeSpace[1] = 15;
                return true;
            } else if((firstColumnIdx == 19 && firstRowIdx == 2) || (firstColumnIdx == 18 && firstRowIdx == 3)){
                this.freeSpace[0] = 6;
                this.freeSpace[1] = 15;
                return true;
            } else if(firstColumnIdx == 19 && firstRowIdx == 3){
                this.freeSpace[0] = 7;
                this.freeSpace[1] = 15;
                return true;
            } else if((firstColumnIdx == 19 && firstRowIdx == 18) || (firstColumnIdx == 18 && firstRowIdx == 17) || (firstColumnIdx == 17 && firstRowIdx == 16)){
                this.freeSpace[0] = 14;
                this.freeSpace[1] = 15;
                return true;
            } else if((firstColumnIdx == 19 && firstRowIdx == 17) || (firstColumnIdx == 18 && firstRowIdx == 16)){
                this.freeSpace[0] = 13;
                this.freeSpace[1] = 15;
                return true;
            } else if(firstColumnIdx == 19 && firstRowIdx == 16){
                this.freeSpace[0] = 12;
                this.freeSpace[1] = 15;
                return true;
            } else if((firstColumnIdx == 18 && firstRowIdx == 19) || (firstColumnIdx == 17 && firstRowIdx == 18) || (firstColumnIdx == 16 && firstRowIdx == 17)){
                this.freeSpace[0] = 15;
                this.freeSpace[1] = 14;
                return true;
            } else if((firstColumnIdx == 17 && firstRowIdx == 19) || (firstColumnIdx == 16 && firstRowIdx == 18)){
                this.freeSpace[0] = 15;
                this.freeSpace[1] = 13;
                return true;
            } else if(firstColumnIdx == 16 && firstRowIdx == 19){
                this.freeSpace[0] = 15;
                this.freeSpace[1] = 12;
                return true;
            }
        } 
        return false;   
    }

    private boolean computerCanWin(){
        // checking for -OOOO, OOOO-, OO-OO, O-OOO, OOO-O in rows, columns, and diagonals
        if(setOfFourInFive("row", this.computerPiece)){
            return true;
        } else if(setOfFourInFive("column", this.computerPiece)){
            return true;
        } else if(setOfFourInFive("diagonalDown", this.computerPiece)){
            return true;
        } else if(setOfFourInFive("diagonalUp", this.computerPiece)){
            return true;
        } else {
            return false;
        }
    }

    private boolean computerCanWinLong(){
        // check for nineteen in a set of twenty
        if(setOfNineteenInTwenty("row", this.computerPiece)){
            return true;
        } else if(setOfNineteenInTwenty("column", this.computerPiece)){
            return true;
        } else if(setOfNineteenInTwenty("diagonalDown", this.computerPiece)){
            return true;
        } else if(setOfNineteenInTwenty("diagonalUp", this.computerPiece)){
            return true;
        // check for fourteen in a set of fifteen
        } else if(setOfFourteenInFifteen("row", this.computerPiece)){
            return true;
        } else if(setOfFourteenInFifteen("column", this.computerPiece)){
            return true;
        } else if(setOfFourteenInFifteen("diagonalDown", this.computerPiece)){
            return true;
        } else if(setOfFourteenInFifteen("diagonalUp", this.computerPiece)){
            return true;
        // check for nine in a row with a free space on one side
        } else if(findRowOfN(9, this.computerPiece)){
            return true;
        } else if(findColumnOfN(9, this.computerPiece)){
            return true;
        } else if(findDiagonalDownOfN(9, this.computerPiece)){
            return true;
        } else if(findDiagonalUpOfN(9, this.computerPiece)){
            return true;
        // check for nine in a set of ten
        } else if(setOfNineInTen("row", this.computerPiece)){
            return true;
        } else if(setOfNineInTen("column", this.computerPiece)){
            return true;
        } else if(setOfNineInTen("diagonalDown", this.computerPiece)){
            return true;
        } else if(setOfNineInTen("diagonalUp", this.computerPiece)){
            return true;
        // checking for -OOOO, OOOO-, OO-OO, O-OOO, OOO-O in rows, columns, and diagonals
        } else if(setOfFourInFive("row", this.computerPiece)){
            return true;
        } else if(setOfFourInFive("column", this.computerPiece)){
            return true;
        } else if(setOfFourInFive("diagonalDown", this.computerPiece)){
            return true;
        } else if(setOfFourInFive("diagonalUp", this.computerPiece)){
            return true;
        } else {
            return false;
        }
    }

    private boolean opponentCanWin(){ 
        // check for four 'X' in a row with a free space on one side
        if(findRowOfN(4, this.opponentPiece)){
            return true;
        } else if(findColumnOfN(4, this.opponentPiece)){
            return true;
        } else if(findDiagonalDownOfN(4, this.opponentPiece)){
            return true;
        } else if(findDiagonalUpOfN(4, this.opponentPiece)){
            return true;
        // checking for XX-XX, X-XXX, XXX-X in rows, columns, and diagonals
        } else if(setOfFourInFive("row", this.opponentPiece)){
            return true;
        } else if(setOfFourInFive("column", this.opponentPiece)){
            return true;
        } else if(setOfFourInFive("diagonalDown", this.opponentPiece)){
            return true;
        } else if(setOfFourInFive("diagonalUp", this.opponentPiece)){
            return true;
        } else {
            return false;
        }
    }

    private boolean opponentCanWinLong(){ 
        // check for nineteen in a set of twenty
        if(setOfNineteenInTwenty("row", this.opponentPiece)){
            return true;
        } else if(setOfNineteenInTwenty("column", this.opponentPiece)){
            return true;
        } else if(setOfNineteenInTwenty("diagonalDown", this.opponentPiece)){
            return true;
        } else if(setOfNineteenInTwenty("diagonalUp", this.opponentPiece)){
            return true;
        // check for fourteen in a set of fifteen
        } else if(setOfFourteenInFifteen("row", this.opponentPiece)){
            return true;
        } else if(setOfFourteenInFifteen("column", this.opponentPiece)){
            return true;
        } else if(setOfFourteenInFifteen("diagonalDown", this.opponentPiece)){
            return true;
        } else if(setOfFourteenInFifteen("diagonalUp", this.opponentPiece)){
            return true;
        // check for nine in a row with a free space on one side
        } else if(findRowOfN(9, this.opponentPiece)){
            return true;
        } else if(findColumnOfN(9, this.opponentPiece)){
            return true;
        } else if(findDiagonalDownOfN(9, this.opponentPiece)){
            return true;
        } else if(findDiagonalUpOfN(9, this.opponentPiece)){
            return true;
        // check for nine in a set of ten
        } else if(setOfNineInTen("row", this.opponentPiece)){
            return true;
        } else if(setOfNineInTen("column", this.opponentPiece)){
            return true;
        } else if(setOfNineInTen("diagonalDown", this.opponentPiece)){
            return true;
        } else if(setOfNineInTen("diagonalUp", this.opponentPiece)){
            return true;
        // check for four in a row with a free space on only one side
        } else if(findRowOfN(4, this.opponentPiece)){
            return true;
        } else if(findColumnOfN(4, this.opponentPiece)){
            return true;
        } else if(findDiagonalDownOfN(4, this.opponentPiece)){
            return true;
        } else if(findDiagonalUpOfN(4, this.opponentPiece)){
            return true;
        // checking for four in a set of five
        } else if(setOfFourInFive("row", this.opponentPiece)){
            return true;
        } else if(setOfFourInFive("column", this.opponentPiece)){
            return true;
        } else if(setOfFourInFive("diagonalDown", this.opponentPiece)){
            return true;
        } else if(setOfFourInFive("diagonalUp", this.opponentPiece)){
            return true;
        } else {
            return false;
        }
    }

    private boolean computerCanMakeFourteenUnblockedInARow(){ 
        // checks if the computer can make nine in a row with neither side blocked
        if(setOfThirteenUnblockedInFifteen("row", this.computerPiece)){
            return true;
        } else if(setOfThirteenUnblockedInFifteen("column", this.computerPiece)){
            return true;
        } else if(setOfThirteenUnblockedInFifteen("diagonalDown", this.computerPiece)){
            return true;
        } else if(setOfThirteenUnblockedInFifteen("diagonalUp", this.computerPiece)){
            return true;
        } else {
            return false;
        }
    }

    private boolean computerCanMakeNineUnblockedInARow(){ 
        // checks if the computer can make nine in a row with neither side blocked
        if(setOfEightUnblockedInTen("row", this.computerPiece)){
            return true;
        } else if(setOfEightUnblockedInTen("column", this.computerPiece)){
            return true;
        } else if(setOfEightUnblockedInTen("diagonalDown", this.computerPiece)){
            return true;
        } else if(setOfEightUnblockedInTen("diagonalUp", this.computerPiece)){
            return true;
        } else {
            return false;
        }
    }

    private boolean computerCanMakeFourUnblockedInARow(){ 
        // This checks the following cases
        // --OOO-, -OOO--, -O-OO-, -OO-O-, O--OO-, -OO--O 
        if(setOfThreeUnblockedInFive("row", this.computerPiece)){
            return true;
        } else if(setOfThreeUnblockedInFive("column", this.computerPiece)){
            return true;
        } else if(setOfThreeUnblockedInFive("diagonalDown", this.computerPiece)){
            return true;
        } else if(setOfThreeUnblockedInFive("diagonalUp", this.computerPiece)){
            return true;
        } else {
            return false;
        }
    }

    private boolean hasEighteenInTwenty(char piece){
        if(piece == this.computerPiece){
            if(setOfEighteenInTwenty("row", piece)){
                return true;
            } else if(setOfEighteenInTwenty("column", piece)){
                return true;
            } else if(setOfEighteenInTwenty("diagonalDown", piece)){
                return true;
            } else if(setOfEighteenInTwenty("diagonalUp", piece)){
                return true;
            } else {
                return false;
            }
        } else {
            return false; // an unblocked set of eighteen in twenty is not possible
        }
    }

    private boolean hasThirteenInFifteen(char piece){
        if(piece == this.computerPiece){
            if(setOfThirteenInFifteen("row", piece)){
                return true;
            } else if(setOfThirteenInFifteen("column", piece)){
                return true;
            } else if(setOfThirteenInFifteen("diagonalDown", piece)){
                return true;
            } else if(setOfThirteenInFifteen("diagonalUp", piece)){
                return true;
            } else {
                return false;
            }
        } else {
            // don't prevent 14 from being formed if one side is blocked 
            if(setOfThirteenUnblockedInFifteen("row", piece)){
                return true;
            } else if(setOfThirteenUnblockedInFifteen("column", piece)){
                return true;
            } else if(setOfThirteenUnblockedInFifteen("diagonalDown", piece)){
                return true;
            } else if(setOfThirteenUnblockedInFifteen("diagonalUp", piece)){
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean hasEightInTen(char piece){
        if(piece == this.computerPiece){
            if(setOfEightInTen("row", piece)){
                return true;
            } else if(setOfEightInTen("column", piece)){
                return true;
            } else if(setOfEightInTen("diagonalDown", piece)){
                return true;
            } else if(setOfEightInTen("diagonalUp", piece)){
                return true;
            } else {
                return false;
            }
        } else {
            // don't prevent 9 from being formed if one side is blocked 
            if(setOfEightUnblockedInTen("row", piece)){
                return true;
            } else if(setOfEightUnblockedInTen("column", piece)){
                return true;
            } else if(setOfEightUnblockedInTen("diagonalDown", piece)){
                return true;
            } else if(setOfEightUnblockedInTen("diagonalUp", piece)){
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean hasThreeInFive(char piece){
        if(piece == this.computerPiece){
            // check senarios O-O-O, OOO--, -OOO-, --OOO, OO-O-, O-OO-, -O-OO, -OO-O for rows, columns, and diagonals
            if(setOfThreeComputerPieceInFive("row")){
                return true;
            } else if(setOfThreeComputerPieceInFive("column")){
                return true;
            } else if(setOfThreeComputerPieceInFive("diagonalDown")){
                return true;
            } else if(setOfThreeComputerPieceInFive("diagonalUp")){
                return true;
            } else {
                return false;
            }
        } else {
            // don't prevent 4 from being formed if one side is blocked 
            // this should check the following cases
            // -X-X-X-, --XXX-, -XXX--, -XX-X-, -X-XX- for rows, columns, and diagonals 
            if(setOfThreeUnblockedInFive("row", this.opponentPiece)){
                return true;
            } else if(setOfThreeUnblockedInFive("column", this.opponentPiece)){
                return true;
            } else if(setOfThreeUnblockedInFive("diagonalDown", this.opponentPiece)){
                return true;
            } else if(setOfThreeUnblockedInFive("diagonalUp", this.opponentPiece)){
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean hasIntersectingSevens(char piece){
        for(int i = 1; i < 19; i++){
            for(int j = 1; j < 19; j++){
                if(this.board[i][j] == '.'){
                    if(i > 7 && j > 7 && nNorth(7, i, j, piece) && nNorthWest(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 7 && j < 12 && nNorth(7, i, j, piece) && nNorthEast(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 7 && j < 12 && nNorth(7, i, j, piece) && nEast(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 7 && j > 7 && nNorth(7, i, j, piece) && nWest(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 7 && i < 12 && j > 7 && nNorth(7, i, j, piece) && nSouthWest(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 7 && i < 12 && j < 12 && nNorth(7, i, j, piece) && nSouthEast(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 7 && i < 12 && j > 7 && nSouth(7, i, j, piece) && nNorthWest(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 7 && i < 12 && j < 12 && nSouth(7, i, j, piece) && nNorthEast(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i < 12 && j < 12 && nSouth(7, i, j, piece) && nEast(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i < 12 && j > 7 && nSouth(7, i, j, piece) && nWest(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i < 12 && j > 7 && nSouth(7, i, j, piece) && nSouthWest(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i < 12 && j < 12 && nSouth(7, i, j, piece) && nSouthEast(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 7 && j > 7 && j < 12 && nEast(7, i, j, piece) && nNorthWest(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 7 && j < 12 && nEast(7, i, j, piece) && nNorthEast(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i < 12 && j > 7 && j < 12 && nEast(7, i, j, piece) && nSouthWest(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i < 12 && j < 12 && nEast(7, i, j, piece) && nSouthEast(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 7 && j > 7 && nWest(7, i, j, piece) && nNorthWest(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 7 && j > 7 && j < 12 && nWest(7, i, j, piece) && nNorthEast(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i < 12 && j > 7 && nWest(7, i, j, piece) && nSouthWest(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i < 12 && j > 7 && j < 12 && nWest(7, i, j, piece) && nSouthEast(7, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean hasIntersectingTwos(char piece){
        for(int i = 1; i < 19; i++){
            for(int j = 1; j < 19; j++){
                if(this.board[i][j] == '.'){
                    if(i > 2 && j > 2 && nNorth(2, i, j, piece) && nNorthWest(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 2 && j < 17 && nNorth(2, i, j, piece) && nNorthEast(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 2 && j < 17 && nNorth(2, i, j, piece) && nEast(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 2 && j > 2 && nNorth(2, i, j, piece) && nWest(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 2 && i < 17 && j > 2 && nNorth(2, i, j, piece) && nSouthWest(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 2 && i < 17 && j < 17 && nNorth(2, i, j, piece) && nSouthEast(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 2 && i < 17 && j > 2 && nSouth(2, i, j, piece) && nNorthWest(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 2 && i < 17 && j < 17 && nSouth(2, i, j, piece) && nNorthEast(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i < 17 && j < 17 && nSouth(2, i, j, piece) && nEast(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i < 17 && j > 2 && nSouth(2, i, j, piece) && nWest(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i < 17 && j > 2 && nSouth(2, i, j, piece) && nSouthWest(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i < 17 && j < 17 && nSouth(2, i, j, piece) && nSouthEast(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 2 && j > 2 && j < 17 && nEast(2, i, j, piece) && nNorthWest(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 2 && j < 17 && nEast(2, i, j, piece) && nNorthEast(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i < 17 && j > 2 && j < 17 && nEast(2, i, j, piece) && nSouthWest(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i < 17 && j < 17 && nEast(2, i, j, piece) && nSouthEast(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 2 && j > 2 && nWest(2, i, j, piece) && nNorthWest(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i > 2 && j > 2 && j < 17 && nWest(2, i, j, piece) && nNorthEast(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i < 17 && j > 2 && nWest(2, i, j, piece) && nSouthWest(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    } else if(i < 17 && j > 2 && j < 17 && nWest(2, i, j, piece) && nSouthEast(2, i, j, piece)){
                        this.freeSpace[0] = i;
                        this.freeSpace[1] = j;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean computerHasTwo(){ 
        // check senarios -O-O-, OO---, O-O--, O--O-, O---O, -OO--, -O--O, --OO-, --O-O, ---OO
        if(setOfTwoInFive("row", this.computerPiece)){
            return true;
        } else if(setOfTwoInFive("column", this.computerPiece)){
            return true;
        } else if(setOfTwoInFive("diagonalDown", this.computerPiece)){
            return true;
        } else if(setOfTwoInFive("diagonalUp", this.computerPiece)){
            return true;
        } else {
            return false;
        }
    }

    private boolean computerHasOne(){ 
        // check senarios O----, -O---, --O--, ---O-, ----O
        if(setOfOneInFive("row", this.computerPiece)){
            return true;
        } else if(setOfOneInFive("column", this.computerPiece)){
            return true;
        } else if(setOfOneInFive("diagonalDown", this.computerPiece)){
            return true;
        } else if(setOfOneInFive("diagonalUp", this.computerPiece)){
            return true;
        } else {
            return false;
        }
    }

    private boolean findRowOfN(int n, char piece){
        for(int i = 0; i < 20; i++){
            if(rowOfN(getRow(i), n, piece)){
                this.freeSpace[0] = i;
                return true;
            } 
        }

        return false;
    }

    private char[] getRow(int n){
        return this.board[n];
    }

    private boolean rowOfN(char[] row, int n, char piece){
        char opposingPiece;
        if(piece == 'X'){
            opposingPiece = 'O';
        } else {
            opposingPiece = 'X';
        }

        int numInRow = 0;
        for(int i = 0; i < 20; i++){
            if(n == 5 && numInRow == n){ // win condition
                return true;
            } else if(i == 4 && n == 4 && numInRow == n && row[i] == '.'){ // four in a row with a free space to the right, left is the wall
                this.freeSpace[1] = i;
                return true;
            } else if(i == 19 && n == 4 && numInRow == 3 && row[i] == piece && row[i-4] == '.'){ // four in a row with a free space to the left, right is wall
                this.freeSpace[1] = i-4;
                return true;
            } else if(i > 4 && n == 4 && numInRow == n && row[i] == '.' && row[i-5] == opposingPiece){ // four in a row with a free space to the right, left is blocked
                this.freeSpace[1] = i;
                return true;
            } else if(i > 4 && n == 4 && numInRow == n && row[i] == opposingPiece && row[i-5] == '.'){ // four in a row with a free space to the left, right is blocked
                this.freeSpace[1] = i-5;
                return true;
            } else if(i == 9 && n == 9 && numInRow == 9 && row[i] == '.'){ // nine in a row with a free space to the right, left is the wall
                this.freeSpace[1] = i;
                return true;
            } else if(i == 19 && n == 9 && numInRow == 8 && row[i] == piece && row[i-9] == '.'){ // nine in a row with a free space to the left, right is the wall
                this.freeSpace[1] = i-9;
                return true;
            } else if(i > 9 && n == 9 && numInRow == 9 && row[i] == '.' && row[i-10] == opposingPiece){ // nine in a row with a free space to the right, left is blocked
                this.freeSpace[1] = i;
                return true;
            } else if(i > 9 && n == 9 && numInRow == n && row[i] == opposingPiece && row[i-10] == '.'){ // nine in a row with a free space to the left, right is blocked
                this.freeSpace[1] = i-10;
                return true;
            } else if(row[i] == piece){
                numInRow++;
            } else if(row[i] != piece){
                numInRow = 0;
            }
        }
        return false;
    }

    private boolean findColumnOfN(int n, char piece){
        for(int i = 0; i < 20; i++){
            if(columnOfN(getColumn(i), n, piece)){
                this.freeSpace[1] = i;
                return true;
            } 
        }

        return false;
    }

    private char[] getColumn(int n){
        char[] column = new char[20];
        for(int i = 0; i < 20; i++){
            column[i] = this.board[i][n];
        }
        return column;
    }

    private boolean columnOfN(char[] column, int n, char piece){
        char opposingPiece;
        if(piece == 'X'){
            opposingPiece = 'O';
        } else {
            opposingPiece = 'X';
        }

        int numInColumn = 0;
        for(int i = 0; i < 19; i++){
            if(n == 5 && numInColumn == n){ // win condition
                return true;
            } else if(i == 4 && n == 4 && numInColumn == n && column[i] == '.'){ // 4 in a column with a free space on the bottom, the top is the wall
                this.freeSpace[0] = i;
                return true;
            } else if(i == 19 && n == 4 && numInColumn == 3 && column[i] == piece && column[i-4] == '.'){ // 4 in a column with a free space on the top, the bottom is the wall
                this.freeSpace[0] = i-4;
                return true;
            } else if(i > 4 && n == 4 && numInColumn == n && column[i] == '.' && column[i-5] == opposingPiece){ // 4 in a column with a free space on the bottom, the top the opposing piece
                this.freeSpace[0] = i;
                return true;
            } else if(i > 4 && n == 4 && numInColumn == n && column[i] == opposingPiece && column[i-5] == '.'){ // 4 in a column with a free space on the top and an opposing piece on bottom
                this.freeSpace[0] = i-5;
                return true;
            } else if(i == 9 && n == 9 && numInColumn == 9 && column[i] == '.'){ // nine in a row with a free space on the bottom, top is the wall
                this.freeSpace[0] = i;
                return true;
            } else if(i == 19 && n == 9 && numInColumn == 8 && column[i] == piece && column[i-9] == '.'){ // nine in a row with a free space on the top, bottom is the wall
                this.freeSpace[0] = i-9;
                return true;
            } else if(i > 9 && n == 9 && numInColumn == n && column[i] == '.' && column[i-10] == opposingPiece){ // nine in a column with a free space to the bottom, top is blocked
                this.freeSpace[0] = i;
                return true;
            } else if(i > 9 && n == 9 && numInColumn == n && column[i] == opposingPiece && column[i-10] == '.'){ // nine in a column with a free space to the top, bottom is blocked
                this.freeSpace[0] = i-10;
                return true;
            } else if(column[i] == piece){
                numInColumn++;
            } else if(column[i] != piece){
                numInColumn = 0;
            }
        }
        return false;
    }

    private boolean findDiagonalDownOfN(int n, char piece){
        for(int i = 0; i <= 30; i++){ // there are 30 possible diagonals of 5 or more
            int rowIdx;
            int columnIdx;
            if(i <= 15){
                rowIdx = 15-i;
                columnIdx = 0;
            } else {
                rowIdx = 0;
                columnIdx = i-15;
            }
            if(diagonalDownOfN(getDiagonalDown(i), n, piece, rowIdx, columnIdx)){
                return true;
            } 
        }

        return false;
    }

    // the downward diagonals are numbered in the following way
    //      diagonals with less than 5 spots are not counted
    // 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30
    // 14 -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    // 13 -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - 
    // 12 -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - 
    // 11 -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    // 10 -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    // 9  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    // 8  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    // 7  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    // 6  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    // 5  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    // 4  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    // 3  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    // 2  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    // 1  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    // 0  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    //    -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    //    -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    //    -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    //    -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    //    -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -

    private char[] getDiagonalDown(int n){
        char[] diagonalDown;
        if(n <= 15){
            diagonalDown = new char[n+5];
            int i = 15-n;
            int j = 0;
            int h = 0;
            while(h < n+5){
                diagonalDown[h] = this.board[i][j];
                j++;
                i++;
                h++;
            }
        } else {
            diagonalDown = new char[30-n+5];
            int i = 0;
            int j = n-15;
            int h = 0;
            while(h < 30-n+5){
                diagonalDown[h] = this.board[i][j];
                j++;
                i++;
                h++;
            }
        } 
        return diagonalDown;
    }

    private boolean diagonalDownOfN(char[] diagonal, int n, char piece, int rowIdx, int columnIdx){
        char opposingPiece;
        if(piece == 'X'){
            opposingPiece = 'O';
        } else {
            opposingPiece = 'X';
        }

        int numInDiagonal = 0;
        for(int i = 0; i < diagonal.length; i++){
            if(n == 5 && numInDiagonal == n){ // win condition
                return true;
            } else if(i == 4 && n == 4 && numInDiagonal == n && diagonal[i] == '.'){ // 4 in a diagonal with a free space on the bottom right, top left is the wall
                this.freeSpace[0] = rowIdx+i;
                this.freeSpace[1] = columnIdx+i;
                return true;
            } else if(i > 4 && n == 4 && numInDiagonal == n && diagonal[i] == '.' && diagonal[i-5] == opposingPiece){ // 4 in a diagonal with a free space on the bottom right, top left is blocked
                this.freeSpace[0] = rowIdx+i;
                this.freeSpace[1] = columnIdx+i;
                return true;
            } else if(i == diagonal.length-1 && n == 4 && numInDiagonal == 3 && diagonal[i] == piece && diagonal[i-4] == '.'){ // 4 in a downward diagonal with a free space on the top left, bottom right is the wall
                this.freeSpace[0] = rowIdx+(i-4);
                this.freeSpace[1] = columnIdx+(i-4);
                return true;
            } else if(i > 4 && n == 4 && numInDiagonal == n && diagonal[i] == opposingPiece && diagonal[i-5] == '.'){ // 4 in a downward diagonal with a free space on the top left, bottom right is blocked
                this.freeSpace[0] = rowIdx+(i-5);
                this.freeSpace[1] = columnIdx+(i-5);
                return true;
            } else if(i == 9 && n == 9 && numInDiagonal == 9 && diagonal[i] == '.'){ // nine in a row with a free space on the bottom, top is the wall
                this.freeSpace[0] = rowIdx+i;
                this.freeSpace[1] = columnIdx+i;
                return true;
            } else if(i == diagonal.length-1 && i > 8 && n == 9 && numInDiagonal == 8 && diagonal[i] == piece && diagonal[i-9] == '.'){ // nine in a row with a free space on the top, bottom is the wall
                this.freeSpace[0] = rowIdx+(i-9);
                this.freeSpace[1] = columnIdx+(i-9);
                return true;
            } else if(i > 9 && n == 9 && numInDiagonal == 9 && diagonal[i] == '.' && diagonal[i-10] == opposingPiece){ // nine in a downward diagonal with a free space to the bottom right, top left is blocked
                this.freeSpace[0] = rowIdx+i;
                this.freeSpace[1] = columnIdx+i;
                return true;
            } else if(i > 9 && n == 9 && numInDiagonal == n && diagonal[i] == opposingPiece && diagonal[i-10] == '.'){ // nine in a downward diagonal with a free space to the top left, bottom right is blocked
                this.freeSpace[0] = rowIdx+(i-10);
                this.freeSpace[1] = columnIdx+(i-10);
                return true;
            } else if(diagonal[i] == piece){
                numInDiagonal++;
            } else if(diagonal[i] != piece){
                numInDiagonal = 0;
            }
        }
        return false;
    }

    private boolean findDiagonalUpOfN(int n, char piece){
        for(int i = 0; i <= 30; i++){ // there are 30 possible diagonals of 5 or more
            int rowIdx;
            int columnIdx;
            if(i <= 15){
                rowIdx = i+4;
                columnIdx = 0;
            } else {
                rowIdx = 19;
                columnIdx = i-15;
            }
            if(diagonalUpOfN(getDiagonalUp(i), n, piece, rowIdx, columnIdx)){
                return true;
            } 
        }

        return false;
    }

    // the upwards diagonals are numbered in the following way
    // diagonals with less than 5 spots are not counted
    //                0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - 16
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - 17
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - 18
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - 19
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - 12  
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - 21
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - 22
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - 23
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - 24
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - 25
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - 26
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - 27
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - 28
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - 29
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - 30
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - 
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  
    // -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - 

    private char[] getDiagonalUp(int n){ 
        char[] diagonalUp;
        if(n <= 15){
            diagonalUp = new char[n+5];
            int i = n+4;
            int j = 0;
            int h = 0;
            while(h < n+5){
                diagonalUp[h] = this.board[i][j];
                j++;
                i--;
                h++;
            }
        } else {
            diagonalUp = new char[30-n+5];
            int i = 19;
            int j = n-15;
            int h = 0;
            while(h < 30-n+5){
                diagonalUp[h] = this.board[i][j];
                j++;
                i--;
                h++;
            }
        } 
        return diagonalUp;
    }

    private boolean diagonalUpOfN(char[] diagonal, int n, char piece, int rowIdx, int columnIdx){ 
        char opposingPiece;
        if(piece == 'X'){
            opposingPiece = 'O';
        } else {
            opposingPiece = 'X';
        }

        int numInDiagonal = 0;
        for(int i = 0; i < diagonal.length; i++){
            if(n == 5 && numInDiagonal == n){ // win condition
                return true;
            } else if(i == 4 && n == 4 && numInDiagonal == n && diagonal[i] == '.'){ // 4 in a diagonal with a free space on the top right, bottom left is the wall
                this.freeSpace[0] = rowIdx-i;
                this.freeSpace[1] = columnIdx+i;
                return true;
            } else if(i > 4 && n == 4 && numInDiagonal == n && diagonal[i] == '.' && diagonal[i-5] == opposingPiece){ // 4 in a diagonal with a free space on the top right, bottom left is blocked
                this.freeSpace[0] = rowIdx-i;
                this.freeSpace[1] = columnIdx+i;
                return true;
            } else if(i == diagonal.length-1 && n == 4 && numInDiagonal == 3 && diagonal[i] == piece && diagonal[i-4] == '.'){ // 4 in a downward diagonal with a free space on the bottom left, top right is the wall
                this.freeSpace[0] = rowIdx-(i-4);
                this.freeSpace[1] = columnIdx+(i-4);
                return true;
            } else if(i > 4 && n == 4 && numInDiagonal == n && diagonal[i] == opposingPiece && diagonal[i-5] == '.'){ // 4 in a downward diagonal with a free space on the bottom left, top right is blocked
                this.freeSpace[0] = rowIdx-(i-5);
                this.freeSpace[1] = columnIdx+(i-5);
                return true;
            } else if(i == 9 && n == 9 && numInDiagonal == 9 && diagonal[i] == '.'){ // nine in a row with a free space on the bottom, top is the wall
                this.freeSpace[0] = rowIdx-i;
                this.freeSpace[1] = columnIdx+i;
                return true;
            } else if(i == diagonal.length-1 && i > 8 && n == 9 && numInDiagonal == 8 && diagonal[i] == piece && diagonal[i-9] == '.'){ // nine in a row with a free space on the top, bottom is the wall
                this.freeSpace[0] = rowIdx-(i-9);
                this.freeSpace[1] = columnIdx+(i-9);
                return true;
            } else if(i > 9 && n == 9 && numInDiagonal == 9 && diagonal[i] == '.' && diagonal[i-10] == opposingPiece){ // nine in a upward diagonal with a free space to the top right, bottom left is blocked
                this.freeSpace[0] = rowIdx-i;
                this.freeSpace[1] = columnIdx+i;
                return true;
            } else if(i > 9 && n == 9 && numInDiagonal == n && diagonal[i] == opposingPiece && diagonal[i-10] == '.'){ // nine in a upward diagonal with a free space to the bottom left, top right is blocked
                this.freeSpace[0] = rowIdx-(i-10);
                this.freeSpace[1] = columnIdx+(i-10);
                return true;
            } else if(diagonal[i] == piece){
                numInDiagonal++;
            } else if(diagonal[i] != piece){
                numInDiagonal = 0;
            }
        }
        return false;
    }

    private boolean setOfNineteenInTwenty(String type, char piece){
        char[] fullSet;
        int rowIdx;
        int columnIdx;
        int blanks;
        int piecesInSet;

        char opposingPiece;
        if(piece == 'X'){
            opposingPiece = 'O';
        } else {
            opposingPiece = 'X';
        }

        if(type == "row"){
            for(int j = 0; j < 20; j++){
                fullSet = getRow(j);
                this.freeSpace[0] = j;
                blanks = 0;
                piecesInSet = 0;

                for(int i = 0; i < 20; i++){
                    if(piecesInSet == 19 && blanks == 1){ 
                        return true;
                    } else if((fullSet[i] == '.' && blanks != 0) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks == 0){
                        blanks++;
                        this.freeSpace[1] = i;
                    } 
                }
            }
            return false;
        } else if(type == "column"){
            for(int j = 0; j < 20; j++){
                fullSet = getColumn(j);
                this.freeSpace[1] = j;
                blanks = 0;
                piecesInSet = 0;

                for(int i = 0; i < 20; i++){
                    if(piecesInSet == 19 && blanks == 1){ 
                        return true;
                    } else if((fullSet[i] == '.' && blanks != 0) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks == 0){
                        blanks++;
                        this.freeSpace[0] = i;
                    } 
                }
            } 
            return false;   
        } else if(type == "diagonalDown"){
            fullSet = getDiagonalDown(15);
            blanks = 0;
            piecesInSet = 0;
        
            rowIdx = 0;
            columnIdx = 0;

            for(int i = 0; i < fullSet.length; i++){ 
                if(piecesInSet == 19 && blanks == 1){ 
                    return true;
                } else if((fullSet[i] == '.' && blanks != 0) || fullSet[i] == opposingPiece){
                    blanks = 0;
                    piecesInSet = 0;
                } else if(fullSet[i] == piece){ 
                    piecesInSet++;
                } else if(fullSet[i] == '.' && blanks == 0){
                    blanks++;
                    this.freeSpace[0] = rowIdx+i;
                    this.freeSpace[1] = columnIdx+i; 
                }
            }
            return false;
        } else if(type == "diagonalUp"){
            fullSet = getDiagonalUp(15);
            blanks = 0;
            piecesInSet = 0;
        
            rowIdx = 19;
            columnIdx = 0;

            for(int i = 0; i < fullSet.length; i++){ 
                if(piecesInSet == 19 && blanks == 1){ 
                    return true;
                } else if((fullSet[i] == '.' && blanks != 0) || fullSet[i] == opposingPiece){
                    blanks = 0;
                    piecesInSet = 0;
                } else if(fullSet[i] == piece){ 
                    piecesInSet++;
                } else if(fullSet[i] == '.' && blanks == 0){
                    blanks++;
                    this.freeSpace[0] = rowIdx-i;
                    this.freeSpace[1] = columnIdx+i; 
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private boolean setOfFourteenInFifteen(String type, char piece){
        char[] fullSet;
        int rowIdx;
        int columnIdx;
        int blanks;
        int piecesInSet;

        char opposingPiece;
        if(piece == 'X'){
            opposingPiece = 'O';
        } else {
            opposingPiece = 'X';
        }

        if(type == "row"){
            for(int j = 0; j < 20; j++){
                fullSet = getRow(j);
                this.freeSpace[0] = j;
                blanks = 0;
                piecesInSet = 0;

                for(int i = 0; i < 20; i++){
                    if(piecesInSet == 14 && blanks == 1){ 
                        return true;
                    } else if((fullSet[i] == '.' && blanks != 0) || (piece == this.opponentPiece && fullSet[i] == '.' && piecesInSet == 0) || (piece == this.opponentPiece && fullSet[i] == '.' && piecesInSet == 14) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks == 0){
                        blanks++;
                        this.freeSpace[1] = i;
                    } 
                }
            }
            return false;
        } else if(type == "column"){
            for(int j = 0; j < 20; j++){
                fullSet = getColumn(j);
                this.freeSpace[1] = j;
                blanks = 0;
                piecesInSet = 0;

                for(int i = 0; i < 20; i++){
                    if(piecesInSet == 14 && blanks == 1){ 
                        return true;
                    } else if((fullSet[i] == '.' && blanks != 0) || (piece == this.opponentPiece && fullSet[i] == '.' && piecesInSet == 0) || (piece == this.opponentPiece && fullSet[i] == '.' && piecesInSet == 14) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks == 0){
                        blanks++;
                        this.freeSpace[0] = i;
                    } 
                }
            } 
            return false;   
        } else if(type == "diagonalDown"){ 
            for(int j = 10; j < 21; j++){
                fullSet = getDiagonalDown(j);
                blanks = 0;
                piecesInSet = 0;
            
                if(j <= 15){
                    rowIdx = 15-j;
                    columnIdx = 0;
                } else {
                    rowIdx = 0;
                    columnIdx = j-15;
                }

                for(int i = 0; i < fullSet.length; i++){ 
                    if(piecesInSet == 14 && blanks == 1){ 
                        return true;
                    } else if((fullSet[i] == '.' && blanks != 0) || (piece == this.opponentPiece && fullSet[i] == '.' && piecesInSet == 0) || (piece == this.opponentPiece && fullSet[i] == '.' && piecesInSet == 14) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks == 0){
                        blanks++;
                        this.freeSpace[0] = rowIdx+i;
                        this.freeSpace[1] = columnIdx+i; 
                    } 
                }
            }
            return false;
        } else if(type == "diagonalUp"){ 
            for(int j = 10; j < 21; j++){
                fullSet = getDiagonalUp(j);
                blanks = 0;
                piecesInSet = 0;
            
                if(j <= 15){
                    rowIdx = j+4;
                    columnIdx = 0;
                } else {
                    rowIdx = 19;
                    columnIdx = j-15;
                }

                for(int i = 0; i < fullSet.length; i++){ 
                    if(piecesInSet == 14 && blanks == 1){ 
                        return true;
                    } else if((fullSet[i] == '.' && blanks != 0) || (piece == this.opponentPiece && fullSet[i] == '.' && piecesInSet == 0) || (piece == this.opponentPiece && fullSet[i] == '.' && piecesInSet == 14) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks == 0){
                        blanks++;
                        this.freeSpace[0] = rowIdx-i;
                        this.freeSpace[1] = columnIdx+i; 
                    } 
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private boolean setOfNineInTen(String type, char piece){
        char[] fullSet;
        int rowIdx;
        int columnIdx;
        int blanks;
        int piecesInSet;

        char opposingPiece;
        if(piece == 'X'){
            opposingPiece = 'O';
        } else {
            opposingPiece = 'X';
        }

        if(type == "row"){
            for(int j = 0; j < 20; j++){
                fullSet = getRow(j);
                this.freeSpace[0] = j;
                blanks = 0;
                piecesInSet = 0;

                for(int i = 0; i < 20; i++){
                    if(piecesInSet == 9 && blanks == 1){ 
                        return true;
                    } else if((fullSet[i] == '.' && blanks != 0) || (piece == this.opponentPiece && fullSet[i] == '.' && piecesInSet == 0) || (piece == this.opponentPiece && fullSet[i] == '.' && piecesInSet == 9) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks == 0){
                        blanks++;
                        this.freeSpace[1] = i;
                    } 
                }
            }
            return false;
        } else if(type == "column"){
            for(int j = 0; j < 20; j++){
                fullSet = getColumn(j);
                this.freeSpace[1] = j;
                blanks = 0;
                piecesInSet = 0;

                for(int i = 0; i < 20; i++){
                    if(piecesInSet == 9 && blanks == 1){ 
                        return true;
                    } else if((fullSet[i] == '.' && blanks != 0) || (piece == this.opponentPiece && fullSet[i] == '.' && piecesInSet == 0) || (piece == this.opponentPiece && fullSet[i] == '.' && piecesInSet == 9) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks == 0){
                        blanks++;
                        this.freeSpace[0] = i;
                    }  
                }
            } 
            return false;   
        } else if(type == "diagonalDown"){ 
            for(int j = 5; j < 26; j++){
                fullSet = getDiagonalDown(j);
                blanks = 0;
                piecesInSet = 0;
            
                if(j <= 15){
                    rowIdx = 15-j;
                    columnIdx = 0;
                } else {
                    rowIdx = 0;
                    columnIdx = j-15;
                }

                for(int i = 0; i < fullSet.length; i++){ 
                    if(piecesInSet == 9 && blanks == 1){ 
                        return true;
                    } else if((fullSet[i] == '.' && blanks != 0) || (piece == this.opponentPiece && fullSet[i] == '.' && piecesInSet == 0) || (piece == this.opponentPiece && fullSet[i] == '.' && piecesInSet == 9) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks == 0){
                        blanks++;
                        this.freeSpace[0] = rowIdx+i;
                        this.freeSpace[1] = columnIdx+i; 
                    }
                }
            }
            return false;
        } else if(type == "diagonalUp"){ 
            for(int j = 5; j < 26; j++){
                fullSet = getDiagonalUp(j);
                blanks = 0;
                piecesInSet = 0;
            
                if(j <= 15){
                    rowIdx = j+4;
                    columnIdx = 0;
                } else {
                    rowIdx = 19;
                    columnIdx = j-15;
                }

                for(int i = 0; i < fullSet.length; i++){ 
                    if(piecesInSet == 9 && blanks == 1){ 
                        return true;
                    } else if((fullSet[i] == '.' && blanks != 0) || (piece == this.opponentPiece && fullSet[i] == '.' && piecesInSet == 0) || (piece == this.opponentPiece && fullSet[i] == '.' && piecesInSet == 9) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks == 0){
                        blanks++;
                        this.freeSpace[0] = rowIdx-i;
                        this.freeSpace[1] = columnIdx+i; 
                    }  
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private boolean setOfEighteenInTwenty(String type, char piece){
        char[] fullSet;
        int rowIdx;
        int columnIdx;
        int blanks;
        int[][] spots = new int[2][2];
        int piecesInSet;

        char opposingPiece;
        if(piece == 'X'){
            opposingPiece = 'O';
        } else {
            opposingPiece = 'X';
        }

        if(type == "row"){
            for(int j = 0; j < 20; j++){
                fullSet = getRow(j);
                blanks = 0;
                piecesInSet = 0;

                for(int i = 0; i < 20; i++){
                    if(piecesInSet == 18 && blanks == 2){ 
                        this.freeSpace = pickOptimalSpot(spots, piece); 
                        return true;
                    } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                        return false;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks < 2){
                        blanks++;
                        int[] singleSpot = {j, i};
                        spots[blanks-1] = singleSpot;
                    } 
                }
            }
            return false;
        } else if(type == "column"){
            for(int j = 0; j < 20; j++){
                fullSet = getColumn(j);
                blanks = 0;
                piecesInSet = 0;

                for(int i = 0; i < 20; i++){
                    if(piecesInSet == 18 && blanks == 2){ 
                        this.freeSpace = pickOptimalSpot(spots, piece); 
                        return true;
                    } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                        return false;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks < 2){
                        blanks++;
                        int[] singleSpot = {i, j};
                        spots[blanks-1] = singleSpot;
                    }  
                }
            } 
            return false;   
        } else if(type == "diagonalDown"){ 
            int j = 15;
            fullSet = getDiagonalDown(j);
            blanks = 0;
            piecesInSet = 0;
        
            if(j <= 15){
                rowIdx = 15-j;
                columnIdx = 0;
            } else {
                rowIdx = 0;
                columnIdx = j-15;
            }

            for(int i = 0; i < fullSet.length; i++){ 
                if(piecesInSet == 18 && blanks == 2){ 
                    this.freeSpace = pickOptimalSpot(spots, piece); 
                    return true;
                } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                    return false;
                } else if(fullSet[i] == piece){ 
                    piecesInSet++;
                } else if(fullSet[i] == '.' && blanks < 2){
                    blanks++;
                    int[] singleSpot = {rowIdx+i, columnIdx+i};
                    spots[blanks-1] = singleSpot;
                } 
            }
            return false;
        } else if(type == "diagonalUp"){ 
            int j = 15;
            fullSet = getDiagonalUp(j);
            blanks = 0;
            piecesInSet = 0;
        
            if(j <= 15){
                rowIdx = j+4;
                columnIdx = 0;
            } else {
                rowIdx = 19;
                columnIdx = j-15;
            }

            for(int i = 0; i < fullSet.length; i++){ 
                if(piecesInSet == 18 && blanks == 2){ 
                    this.freeSpace = pickOptimalSpot(spots, piece); 
                    return true;
                } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                    return false;
                } else if(fullSet[i] == piece){ 
                    piecesInSet++;
                } else if(fullSet[i] == '.' && blanks < 2){
                    blanks++;
                    int[] singleSpot = {rowIdx-i, columnIdx+i};
                    spots[blanks-1] = singleSpot;
                } 
            }
            return false;
        } else {
            return false;
        }
    }

    private boolean setOfThirteenUnblockedInFifteen(String type, char piece){
        char[] fullSet;
        int rowIdx;
        int columnIdx;
        int blanks;
        int[][] spots = new int[2][2];
        int piecesInSet;

        char opposingPiece;
        if(piece == 'X'){
            opposingPiece = 'O';
        } else {
            opposingPiece = 'X';
        }

        if(type == "row"){
            for(int j = 0; j < 20; j++){
                fullSet = getRow(j);
                blanks = 0;
                piecesInSet = 0;

                for(int i = 1; i < 19; i++){
                    if(piecesInSet == 13 && blanks == 2){ 
                        this.freeSpace = pickOptimalSpot(spots, piece); 
                        return true;
                    } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks < 2){
                        blanks++;
                        int[] singleSpot = {j, i};
                        spots[blanks-1] = singleSpot;
                    } 
                }
            }
            return false;
        } else if(type == "column"){
            for(int j = 0; j < 20; j++){
                fullSet = getColumn(j);
                blanks = 0;
                piecesInSet = 0;

                for(int i = 1; i < 19; i++){
                    if(piecesInSet == 13 && blanks == 2){ 
                        this.freeSpace = pickOptimalSpot(spots, piece); 
                        return true;
                    } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks < 2){
                        blanks++;
                        int[] singleSpot = {i, j};
                        spots[blanks-1] = singleSpot;
                    }  
                }
            } 
            return false;   
        } else if(type == "diagonalDown"){ 
            for(int j = 10; j < 21; j++){
                fullSet = getDiagonalDown(j);
                blanks = 0;
                piecesInSet = 0;
            
                if(j <= 15){
                    rowIdx = 15-j;
                    columnIdx = 0;
                } else {
                    rowIdx = 0;
                    columnIdx = j-15;
                }

                for(int i = 1; i < fullSet.length-1; i++){ 
                    if(piecesInSet == 13 && blanks == 2){ 
                        this.freeSpace = pickOptimalSpot(spots, piece); 
                        return true;
                    } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks < 2){
                        blanks++;
                        int[] singleSpot = {rowIdx+i, columnIdx+i};
                        spots[blanks-1] = singleSpot;
                    } 
                }
            }
            return false;
        } else if(type == "diagonalUp"){ 
            for(int j = 10; j < 21; j++){
                fullSet = getDiagonalUp(j);
                blanks = 0;
                piecesInSet = 0;
            
                if(j <= 15){
                    rowIdx = j+4;
                    columnIdx = 0;
                } else {
                    rowIdx = 19;
                    columnIdx = j-15;
                }

                for(int i = 1; i < fullSet.length-1; i++){ 
                    if(piecesInSet == 13 && blanks == 2){ 
                        this.freeSpace = pickOptimalSpot(spots, piece); 
                        return true;
                    } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks < 2){
                        blanks++;
                        int[] singleSpot = {rowIdx-i, columnIdx+i};
                        spots[blanks-1] = singleSpot;
                    } 
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private boolean setOfThirteenInFifteen(String type, char piece){
        char[] fullSet;
        int rowIdx;
        int columnIdx;
        int blanks;
        int[][] spots = new int[2][2];
        int piecesInSet;

        char opposingPiece;
        if(piece == 'X'){
            opposingPiece = 'O';
        } else {
            opposingPiece = 'X';
        }

        if(type == "row"){
            for(int j = 0; j < 20; j++){
                fullSet = getRow(j);
                blanks = 0;
                piecesInSet = 0;

                for(int i = 0; i < 20; i++){
                    if(piecesInSet == 13 && blanks == 2){ 
                        this.freeSpace = pickOptimalSpot(spots, piece); 
                        return true;
                    } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks < 2){
                        blanks++;
                        int[] singleSpot = {j, i};
                        spots[blanks-1] = singleSpot;
                    } 
                }
            }
            return false;
        } else if(type == "column"){
            for(int j = 0; j < 20; j++){
                fullSet = getColumn(j);
                blanks = 0;
                piecesInSet = 0;

                for(int i = 0; i < 20; i++){
                    if(piecesInSet == 13 && blanks == 2){ 
                        this.freeSpace = pickOptimalSpot(spots, piece); 
                        return true;
                    } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks < 2){
                        blanks++;
                        int[] singleSpot = {i, j};
                        spots[blanks-1] = singleSpot;
                    }  
                }
            } 
            return false;   
        } else if(type == "diagonalDown"){ 
            for(int j = 10; j < 21; j++){
                fullSet = getDiagonalDown(j);
                blanks = 0;
                piecesInSet = 0;
            
                if(j <= 15){
                    rowIdx = 15-j;
                    columnIdx = 0;
                } else {
                    rowIdx = 0;
                    columnIdx = j-15;
                }

                for(int i = 0; i < fullSet.length; i++){ 
                    if(piecesInSet == 13 && blanks == 2){ 
                        this.freeSpace = pickOptimalSpot(spots, piece); 
                        return true;
                    } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks < 2){
                        blanks++;
                        int[] singleSpot = {rowIdx+i, columnIdx+i};
                        spots[blanks-1] = singleSpot;
                    } 
                }
            }
            return false;
        } else if(type == "diagonalUp"){ 
            for(int j = 10; j < 21; j++){
                fullSet = getDiagonalUp(j);
                blanks = 0;
                piecesInSet = 0;
            
                if(j <= 15){
                    rowIdx = j+4;
                    columnIdx = 0;
                } else {
                    rowIdx = 19;
                    columnIdx = j-15;
                }

                for(int i = 0; i < fullSet.length; i++){ 
                    if(piecesInSet == 13 && blanks == 2){ 
                        this.freeSpace = pickOptimalSpot(spots, piece); 
                        return true;
                    } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks < 2){
                        blanks++;
                        int[] singleSpot = {rowIdx-i, columnIdx+i};
                        spots[blanks-1] = singleSpot;
                    } 
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private boolean setOfEightUnblockedInTen(String type, char piece){
        char[] fullSet;
        int rowIdx;
        int columnIdx;
        int blanks;
        int[][] spots = new int[2][2];
        int piecesInSet;

        char opposingPiece;
        if(piece == 'X'){
            opposingPiece = 'O';
        } else {
            opposingPiece = 'X';
        }

        if(type == "row"){
            for(int j = 0; j < 20; j++){
                fullSet = getRow(j);
                blanks = 0;
                piecesInSet = 0;

                for(int i = 1; i < 19; i++){
                    if(piecesInSet == 8 && blanks == 2){ 
                        this.freeSpace = pickOptimalSpot(spots, piece); 
                        return true;
                    } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks < 2){
                        blanks++;
                        int[] singleSpot = {j, i};
                        spots[blanks-1] = singleSpot;
                    } 
                }
            }
            return false;
        } else if(type == "column"){
            for(int j = 0; j < 20; j++){
                fullSet = getColumn(j);
                blanks = 0;
                piecesInSet = 0;

                for(int i = 1; i < 19; i++){
                    if(piecesInSet == 8 && blanks == 2){ 
                        this.freeSpace = pickOptimalSpot(spots, piece); 
                        return true;
                    } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks < 2){
                        blanks++;
                        int[] singleSpot = {i, j};
                        spots[blanks-1] = singleSpot;
                    }  
                }
            } 
            return false;   
        } else if(type == "diagonalDown"){ 
            for(int j = 5; j < 26; j++){
                fullSet = getDiagonalDown(j);
                blanks = 0;
                piecesInSet = 0;
            
                if(j <= 15){
                    rowIdx = 15-j;
                    columnIdx = 0;
                } else {
                    rowIdx = 0;
                    columnIdx = j-15;
                }

                for(int i = 1; i < fullSet.length-1; i++){ 
                    if(piecesInSet == 8 && blanks == 2){ 
                        this.freeSpace = pickOptimalSpot(spots, piece); 
                        return true;
                    } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks < 2){
                        blanks++;
                        int[] singleSpot = {rowIdx+i, columnIdx+i};
                        spots[blanks-1] = singleSpot;
                    } 
                }
            }
            return false;
        } else if(type == "diagonalUp"){ 
            for(int j = 5; j < 26; j++){
                fullSet = getDiagonalUp(j);
                blanks = 0;
                piecesInSet = 0;
            
                if(j <= 15){
                    rowIdx = j+4;
                    columnIdx = 0;
                } else {
                    rowIdx = 19;
                    columnIdx = j-15;
                }

                for(int i = 1; i < fullSet.length-1; i++){ 
                    if(piecesInSet == 8 && blanks == 2){ 
                        this.freeSpace = pickOptimalSpot(spots, piece); 
                        return true;
                    } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks < 2){
                        blanks++;
                        int[] singleSpot = {rowIdx-i, columnIdx+i};
                        spots[blanks-1] = singleSpot;
                    } 
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private boolean setOfEightInTen(String type, char piece){
        char[] fullSet;
        int rowIdx;
        int columnIdx;
        int blanks;
        int[][] spots = new int[2][2];
        int piecesInSet;

        char opposingPiece;
        if(piece == 'X'){
            opposingPiece = 'O';
        } else {
            opposingPiece = 'X';
        }

        if(type == "row"){
            for(int j = 0; j < 20; j++){
                fullSet = getRow(j);
                blanks = 0;
                piecesInSet = 0;

                for(int i = 0; i < 20; i++){
                    if(piecesInSet == 8 && blanks == 2){ 
                        this.freeSpace = pickOptimalSpot(spots, piece); 
                        return true;
                    } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks < 2){
                        blanks++;
                        int[] singleSpot = {j, i};
                        spots[blanks-1] = singleSpot;
                    } 
                }
            }
            return false;
        } else if(type == "column"){
            for(int j = 0; j < 20; j++){
                fullSet = getColumn(j);
                blanks = 0;
                piecesInSet = 0;

                for(int i = 0; i < 20; i++){
                    if(piecesInSet == 8 && blanks == 2){ 
                        this.freeSpace = pickOptimalSpot(spots, piece); 
                        return true;
                    } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks < 2){
                        blanks++;
                        int[] singleSpot = {i, j};
                        spots[blanks-1] = singleSpot;
                    }  
                }
            } 
            return false;   
        } else if(type == "diagonalDown"){ 
            for(int j = 5; j < 26; j++){
                fullSet = getDiagonalDown(j);
                blanks = 0;
                piecesInSet = 0;
            
                if(j <= 15){
                    rowIdx = 15-j;
                    columnIdx = 0;
                } else {
                    rowIdx = 0;
                    columnIdx = j-15;
                }

                for(int i = 0; i < fullSet.length; i++){ 
                    if(piecesInSet == 8 && blanks == 2){ 
                        this.freeSpace = pickOptimalSpot(spots, piece); 
                        return true;
                    } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks < 2){
                        blanks++;
                        int[] singleSpot = {rowIdx+i, columnIdx+i};
                        spots[blanks-1] = singleSpot;
                    } 
                }
            }
            return false;
        } else if(type == "diagonalUp"){ 
            for(int j = 5; j < 26; j++){
                fullSet = getDiagonalUp(j);
                blanks = 0;
                piecesInSet = 0;
            
                if(j <= 15){
                    rowIdx = j+4;
                    columnIdx = 0;
                } else {
                    rowIdx = 19;
                    columnIdx = j-15;
                }

                for(int i = 0; i < fullSet.length; i++){ 
                    if(piecesInSet == 8 && blanks == 2){ 
                        this.freeSpace = pickOptimalSpot(spots, piece); 
                        return true;
                    } else if((fullSet[i] == '.' && blanks >= 2) || fullSet[i] == opposingPiece){
                        blanks = 0;
                        piecesInSet = 0;
                    } else if(fullSet[i] == piece){ 
                        piecesInSet++;
                    } else if(fullSet[i] == '.' && blanks < 2){
                        blanks++;
                        int[] singleSpot = {rowIdx-i, columnIdx+i};
                        spots[blanks-1] = singleSpot;
                    } 
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private boolean setOfFourInFive(String type, char piece){ 
        char[] fullSet;
        int rowIdx;
        int columnIdx;
        if(type == "row"){
            for(int j = 0; j < 20; j++){
                fullSet = getRow(j);
                this.freeSpace[0] = j;

                for(int i = 0; i < 15; i++){
                    if(i == 14 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] =='.' && fullSet[i+4] == piece && fullSet[i+5] == piece){ // check senario -OO-OO where the right is the wall
                        this.freeSpace[1] = i+3;
                        return true;
                    } else if(i == 0 && fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] != piece){ // check senario OO-OO- where the left is the wall
                        this.freeSpace[1] = i+2;
                        return true;
                    } else if(i < 14 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] == piece && fullSet[i+6] != piece){ // check senario -OO-OO-
                        this.freeSpace[1] = i+3;
                        return true;
                    } else if(i == 14 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.' && fullSet[i+5] == piece){ // check senario -OOO-O where the right is the wall
                        this.freeSpace[1] = i+4;
                        return true;
                    } else if(i == 0 && fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] != piece){ // check senario OOO-O- where the left is the wall
                        this.freeSpace[1] = i+3;
                        return true;
                    } else if(i < 14 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.' && fullSet[i+5] == piece && fullSet[i+6] != piece){ // check senario -OOO-O-
                        this.freeSpace[1] = i+4;
                        return true;
                    } else if(i == 14 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == piece){ // check senario -O-OOO right is the wall
                        this.freeSpace[1] = i+2;
                        return true;
                    } else if(i == 0 && fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] != piece){ // check senario O-OOO- left is the wall
                        this.freeSpace[1] = i+1;
                        return true;
                    } else if(i < 14 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == piece && fullSet[i+6] != piece){ // check senario -O-OOO-
                        this.freeSpace[1] = i+2;
                        return true;
                    } else if(i < 14 && piece == this.computerPiece && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.' && fullSet[i+6] != piece){ // check senario -OOOO-- 
                        this.freeSpace[1] = i+5;
                        return true;
                    }  else if(i < 14 && piece == this.computerPiece && fullSet[i] != piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == piece && fullSet[i+6] != piece){ // check senario --OOOO- 
                        this.freeSpace[1] = i+1;
                        return true;
                    } else if((((i < 14 && piece == this.opponentPiece) || (i == 0 && piece == this.computerPiece) || (i == 14 && piece == this.computerPiece))) && i < 15 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario -OOOO- 
                        int[][] spots = {{freeSpace[0], i}, {freeSpace[0], i+5}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } 
                }
            }
            return false;
        } else if(type == "column"){
            for(int j = 0; j < 20; j++){
                fullSet = getColumn(j);
                this.freeSpace[1] = j;

                for(int i = 0; i < 15; i++){
                    if(i == 14 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] =='.' && fullSet[i+4] == piece && fullSet[i+5] == piece){ // check senario -OO-OO where the top is the wall
                        this.freeSpace[0] = i+3;
                        return true;
                    } else if(i == 0 && fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] != piece){ // check senario OO-OO- where the bottom is the wall
                        this.freeSpace[0] = i+2;
                        return true;
                    } else if(i < 14 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] == piece && fullSet[i+6] != piece){ // check senario -OO-OO-
                        this.freeSpace[0] = i+3;
                        return true;
                    } else if(i == 14 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.' && fullSet[i+5] == piece){ // check senario -OOO-O where the top is the wall
                        this.freeSpace[0] = i+4;
                        return true;
                    } else if(i == 0 && fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] != piece){ // check senario OOO-O- where the bottom is the wall
                        this.freeSpace[0] = i+3;
                        return true;
                    } else if(i < 14 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.' && fullSet[i+5] == piece && fullSet[i+6] != piece){ // check senario -OOO-O-
                        this.freeSpace[0] = i+4;
                        return true;
                    } else if(i == 14 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == piece){ // check senario -O-OOO top is the wall
                        this.freeSpace[0] = i+2;
                        return true;
                    } else if(i == 0 && fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] != piece){ // check senario O-OOO- bottom is the wall
                        this.freeSpace[0] = i+1;
                        return true;
                    } else if(i < 14 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == piece && fullSet[i+6] != piece){ // check senario -O-OOO-
                        this.freeSpace[0] = i+2;
                        return true;
                    } else if(i < 14 && piece == this.computerPiece && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.' && fullSet[i+6] != piece){ // check senario -OOOO-- 
                        this.freeSpace[0] = i+5; 
                        return true;
                    } else if(i < 14 && piece == this.computerPiece && fullSet[i] != piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == piece && fullSet[i+6] != piece){ // check senario --OOOO-
                        this.freeSpace[0] = i+1;
                        return true;
                    } else if((((i < 14 && piece == this.opponentPiece) || (i == 0 && piece == this.computerPiece) || (i == 14 && piece == this.computerPiece))) && i < 15 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario -OOOO- 
                        int[][] spots = {{i, freeSpace[1]}, {i+5, freeSpace[1]}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    }
                }
            } 
            return false;   
        } else if(type == "diagonalDown"){ 
            for(int j = 0; j < 31; j++){
                fullSet = getDiagonalDown(j);
            
                if(j <= 15){
                    rowIdx = 15-j;
                    columnIdx = 0;
                } else {
                    rowIdx = 0;
                    columnIdx = j-15;
                }

                if(fullSet.length == 5 || fullSet.length == 6){
                    if(fullSet[0] == piece && fullSet[1] == piece && fullSet[2] == '.' && fullSet[3] == piece && fullSet[4] == piece){ // check senario OO-OO
                        this.freeSpace[0] = rowIdx+2;
                        this.freeSpace[1] = columnIdx+2;
                        return true;
                    } else if(fullSet[0] == piece && fullSet[1] == piece && fullSet[2] == piece && fullSet[3] == '.' && fullSet[4] == piece){ // check senario OOO-O
                        this.freeSpace[0] = rowIdx+3;
                        this.freeSpace[1] = columnIdx+3;
                        return true;
                    } else if(fullSet[0] == piece && fullSet[1] == '.' && fullSet[2] == piece && fullSet[3] == piece && fullSet[4] == piece){ // check senario O-OOO
                        this.freeSpace[0] = rowIdx+1;
                        this.freeSpace[1] = columnIdx+1;
                        return true;
                    } else if(piece == this.computerPiece && fullSet[0] == '.' && fullSet[1] == piece && fullSet[2] == piece && fullSet[3] == piece && fullSet[4] == piece){ // check senario -OOOO 
                        this.freeSpace[0] = rowIdx;
                        this.freeSpace[1] = columnIdx; 
                        return true;
                    } else if(piece == this.computerPiece && fullSet[0] == piece && fullSet[1] == piece && fullSet[2] == piece && fullSet[3] == piece && fullSet[4] == '.'){ // check senario OOOO- 
                        this.freeSpace[0] = rowIdx+4;
                        this.freeSpace[1] = columnIdx+4;
                        return true;
                    }
                } else {
                    for(int i = 0; i < fullSet.length-5; i++){ 
                        if(i == 14 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] =='.' && fullSet[i+4] == piece && fullSet[i+5] == piece){ // check senario -OO-OO where the bottom right is the wall
                            this.freeSpace[0] = rowIdx+i+3;
                            this.freeSpace[1] = columnIdx+i+3;
                            return true;
                        } else if(i == 0 && fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] != piece){ // check senario OO-OO- where the top left is the wall
                            this.freeSpace[0] = rowIdx+i+2;
                            this.freeSpace[1] = columnIdx+i+2;
                            return true;
                        } else if(i < fullSet.length-6 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] == piece && fullSet[i+6] != piece){ // check senario -OO-OO-
                            this.freeSpace[0] = rowIdx+i+3;
                            this.freeSpace[1] = columnIdx+i+3;
                            return true;
                        } else if(i == 14 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.' && fullSet[i+5] == piece){ // check senario -OOO-O where the bottom right is the wall
                            this.freeSpace[0] = rowIdx+i+4;
                            this.freeSpace[1] = columnIdx+i+4;
                            return true;
                        } else if(i == 0 && fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] != piece){ // check senario OOO-O- where the top left is the wall
                            this.freeSpace[0] = rowIdx+i+3;
                            this.freeSpace[1] = columnIdx+i+3;
                            return true;
                        } else if(i < fullSet.length-6 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.' && fullSet[i+5] == piece && fullSet[i+6] != piece){ // check senario -OOO-O-
                            this.freeSpace[0] = rowIdx+i+4;
                            this.freeSpace[1] = columnIdx+i+4;
                            return true;
                        } else if(i == 14 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == piece){ // check senario -O-OOO bottom right is the wall
                            this.freeSpace[0] = rowIdx+i+2;
                            this.freeSpace[1] = columnIdx+i+2;
                            return true;
                        } else if(i == 0 && fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] != piece){ // check senario O-OOO- top left is the wall
                            this.freeSpace[0] = rowIdx+i+1;
                            this.freeSpace[1] = columnIdx+i+1;
                            return true;
                        } else if(i < fullSet.length-6 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == piece && fullSet[i+6] != piece){ // check senario -O-OOO-
                            this.freeSpace[0] = rowIdx+i+2;
                            this.freeSpace[1] = columnIdx+i+2;
                            return true;
                        } else if(i < fullSet.length-6 && piece == this.computerPiece && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.' && fullSet[i+6] != piece){ // check senario -OOOO-- 
                            this.freeSpace[0] = rowIdx+i+5;
                            this.freeSpace[1] = columnIdx+i+5; 
                            return true;
                        } else if(i < fullSet.length-6 && piece == this.computerPiece && fullSet[i] != piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == piece && fullSet[i+6] != piece){ // check senario --OOOO-
                            this.freeSpace[0] = rowIdx+i+1;
                            this.freeSpace[1] = columnIdx+i+1;
                            return true;
                        } else if((((i < 14 && piece == this.opponentPiece) || (i == 0 && piece == this.computerPiece) || (i == 14 && piece == this.computerPiece))) && i < 15 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario -OOOO- 
                            int[][] spots = {{rowIdx+i, columnIdx+i}, {rowIdx+i+5, columnIdx+i+5}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        } 
                    }
                }
            }
            return false;
        } else if(type == "diagonalUp"){ 
            for(int j = 0; j < 31; j++){
                fullSet = getDiagonalUp(j);
            
                if(j <= 15){
                    rowIdx = j+4;
                    columnIdx = 0;
                } else {
                    rowIdx = 19;
                    columnIdx = j-15;
                }
                
                if(fullSet.length == 5 || fullSet.length == 6){
                    if(fullSet[0] == piece && fullSet[1] == piece && fullSet[2] == '.' && fullSet[3] == piece && fullSet[4] == piece){ // check senario OO-OO
                        this.freeSpace[0] = rowIdx-2;
                        this.freeSpace[1] = columnIdx+2;
                        return true;
                    } else if(fullSet[0] == piece && fullSet[1] == piece && fullSet[2] == piece && fullSet[3] == '.' && fullSet[4] == piece){ // check senario OOO-O
                        this.freeSpace[0] = rowIdx-3;
                        this.freeSpace[1] = columnIdx+3;
                        return true;
                    } else if(fullSet[0] == piece && fullSet[1] == '.' && fullSet[2] == piece && fullSet[3] == piece && fullSet[4] == piece){ // check senario O-OOO
                        this.freeSpace[0] = rowIdx-1;
                        this.freeSpace[1] = columnIdx+1;
                        return true;
                    } else if(piece == this.computerPiece && fullSet[0] == '.' && fullSet[1] == piece && fullSet[2] == piece && fullSet[3] == piece && fullSet[4] == piece){ // check senario -OOOO 
                        this.freeSpace[0] = rowIdx;
                        this.freeSpace[1] = columnIdx; 
                        return true;
                    } else if(piece == this.computerPiece && fullSet[0] == piece && fullSet[1] == piece && fullSet[2] == piece && fullSet[3] == piece && fullSet[4] == '.'){ // check senario OOOO- 
                        this.freeSpace[0] = rowIdx-4;
                        this.freeSpace[1] = columnIdx+4;
                        return true;
                    }
                } else {
                    for(int i = 0; i < fullSet.length-6; i++){ 
                        if(i == 14 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] == piece){ // check senario -OO-OO where the top right is the wall
                            this.freeSpace[0] = rowIdx-(i+3);
                            this.freeSpace[1] = columnIdx+i+3;
                            return true;
                        } else if(i == 0 && fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] != piece){ // check senario OO-OO- where the bottom left is the wall
                            this.freeSpace[0] = rowIdx-(i+2);
                            this.freeSpace[1] = columnIdx+i+2;
                            return true;
                        } else if(fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] == piece && fullSet[i+6] != piece){ // check senario -OO-OO-
                            this.freeSpace[0] = rowIdx-(i+3);
                            this.freeSpace[1] = columnIdx+i+3;
                            return true;
                        } else if(i == 14 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.' && fullSet[i+5] == piece){ // check senario -OOO-O where the top right is the wall
                            this.freeSpace[0] = rowIdx-(i+4);
                            this.freeSpace[1] = columnIdx+i+4;
                            return true;
                        } else if(i == 0 && fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] != piece){ // check senario OOO-O- where the bottom left is the wall
                            this.freeSpace[0] = rowIdx-(i+3);
                            this.freeSpace[1] = columnIdx+i+3;
                            return true;
                        } else if(fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.' && fullSet[i+5] == piece && fullSet[i+6] != piece){ // check senario -OOO-O-
                            this.freeSpace[0] = rowIdx-(i+4);
                            this.freeSpace[1] = columnIdx+i+4;
                            return true;
                        } else if(i == 14 && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == piece){ // check senario -O-OOO top right is the wall
                            this.freeSpace[0] = rowIdx-(i+2);
                            this.freeSpace[1] = columnIdx+i+2;
                            return true;
                        } else if(i == 0 && fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] != piece){ // check senario O-OOO- bottom left is the wall
                            this.freeSpace[0] = rowIdx-(i+1);
                            this.freeSpace[1] = columnIdx+i+1;
                            return true;
                        } else if(fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == piece && fullSet[i+6] != piece){ // check senario -O-OOO-
                            this.freeSpace[0] = rowIdx-(i+2);
                            this.freeSpace[1] = columnIdx+i+2;
                            return true;
                        } else if(piece == this.computerPiece && fullSet[i] != piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.' && fullSet[i+6] != piece){ // check senario -OOOO-- 
                            this.freeSpace[0] = rowIdx-(i+5);
                            this.freeSpace[1] = columnIdx+i+5; 
                            return true;
                        } else if(piece == this.computerPiece && fullSet[i] != piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == piece && fullSet[i+6] != piece){ // check senario --OOOO-
                            this.freeSpace[0] = rowIdx-(i+1);
                            this.freeSpace[1] = columnIdx+i+1;
                            return true;
                        } else if((((i < 14 && piece == this.opponentPiece) || (i == 0 && piece == this.computerPiece) || (i == 14 && piece == this.computerPiece))) && i < 15 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario -OOOO- 
                            int[][] spots = {{rowIdx-i, columnIdx+i}, {rowIdx-(i+5), columnIdx+i+5}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        } 
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private boolean setOfThreeComputerPieceInFive(String type){
        char[] fullSet;
        int rowIdx;
        int columnIdx;
        char piece = this.computerPiece;
        if(type == "row"){
            for(int j = 0; j < 20; j++){
                fullSet = getRow(j);
                this.freeSpace[0] = j;

                for(int i = 0; i < 16; i++){
                    if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario O-O-O
                        int[][] spots = {{this.freeSpace[0], i+1}, {this.freeSpace[0], i+3}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } else if(fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario OOO--
                        this.freeSpace[1] = i+3;
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario -OOO-
                        if(i == 0){
                            this.freeSpace[1] = i+4;
                        } else if(i == 15){
                            this.freeSpace[1] = i;
                        } else {
                            int[][] spots = {{this.freeSpace[0], i}, {this.freeSpace[0], i+4}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        }
                    } else if(fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece){ // check senario --OOO
                        this.freeSpace[1] = i+1;
                        return true;
                    } else if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario O-OO-
                        this.freeSpace[1] = i+1;
                        return true;
                    } else if(fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario OO-O-
                        this.freeSpace[1] = i+2;
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece){ // check senario -O-OO,
                        this.freeSpace[1] = i+2;
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario -OO-O
                        this.freeSpace[1] = i+3;
                        return true;
                    } else if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece){ // check senario O--OO,
                        int[][] spots = {{this.freeSpace[0], i+1}, {this.freeSpace[0], i+2}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } else if(fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario OO--O
                        int[][] spots = {{this.freeSpace[0], i+2}, {this.freeSpace[0], i+3}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } 
                }
            }
            return false;
        } else if(type == "column"){
            for(int j = 0; j < 20; j++){
                fullSet = getColumn(j);
                this.freeSpace[1] = j;

                for(int i = 0; i < 16; i++){
                    if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario O-O-O
                        int[][] spots = {{i+1, this.freeSpace[1]}, {i+3, this.freeSpace[1]}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                    } else if(fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario OOO--
                        this.freeSpace[0] = i+3;
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario -OOO-
                        if(i == 0){
                            this.freeSpace[0] = i+4;
                        } else if(i == 15){
                            this.freeSpace[0] = i;
                        } else {
                            int[][] spots = {{i, this.freeSpace[1]}, {i+4, this.freeSpace[1]}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        }
                    } else if(fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece){ // check senario --OOO
                        this.freeSpace[0] = i+1;
                        return true;
                    } else if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario O-OO-
                        this.freeSpace[0] = i+1;
                        return true;
                    } else if(fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario OO-O-
                        this.freeSpace[0] = i+2;
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece){ // check senario -O-OO,
                        this.freeSpace[0] = i+2;
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario -OO-O
                        this.freeSpace[0] = i+3;
                        return true;
                    } else if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece){ // check senario O--OO
                        int[][] spots = {{i+1, this.freeSpace[1]}, {i+2, this.freeSpace[1]}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } else if(fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario OO--O
                        int[][] spots = {{i+2, this.freeSpace[1]}, {i+3, this.freeSpace[1]}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } 
                }
            } 
            return false;   
        } else if(type == "diagonalDown"){
            for(int j = 0; j < 31; j++){
                fullSet = getDiagonalDown(j);
            
                if(j <= 15){
                    rowIdx = 15-j;
                    columnIdx = 0;
                } else {
                    rowIdx = 0;
                    columnIdx = j-15;
                }

                for(int i = 0; i < fullSet.length-4; i++){ 
                    if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario O-O-O
                        int[][] spots = {{rowIdx+i+1, columnIdx+i+1}, {rowIdx+i+3, columnIdx+i+3}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } else if(fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario OOO--
                        this.freeSpace[0] = rowIdx+i+4;
                        this.freeSpace[1] = columnIdx+i+4;
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario -OOO-
                        if(i == 0){
                            this.freeSpace[0] = rowIdx+i+4;
                            this.freeSpace[1] = columnIdx+i+4;
                        } else if(i == fullSet.length-5){
                            this.freeSpace[0] = rowIdx+i;
                            this.freeSpace[1] = columnIdx+i;
                        } else {
                            int[][] spots = {{rowIdx+i, columnIdx+i}, {rowIdx+i+4, columnIdx+i+4}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        }
                    } else if(fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece){ // check senario --OOO
                        this.freeSpace[0] = rowIdx+i+1;
                        this.freeSpace[1] = columnIdx+i+1;
                        return true;
                    } else if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario O-OO-
                        this.freeSpace[0] = rowIdx+i+1;
                        this.freeSpace[1] = columnIdx+i+1;
                        return true;
                    } else if(fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario OO-O-
                        this.freeSpace[0] = rowIdx+i+2;
                        this.freeSpace[1] = columnIdx+i+2;
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece){ // check senario -O-OO,
                        this.freeSpace[0] = rowIdx+i+2;
                        this.freeSpace[1] = columnIdx+i+2;
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario -OO-O
                        this.freeSpace[0] = rowIdx+i+3;
                        this.freeSpace[1] = columnIdx+i+3;
                        return true;
                    } else if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece){ // check senario O--OO
                        int[][] spots = {{rowIdx+i+1, columnIdx+i+1}, {rowIdx+i+2, columnIdx+i+2}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } else if(fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario OO--O
                        int[][] spots = {{rowIdx+i+2, columnIdx+i+2}, {rowIdx+i+3, columnIdx+i+3}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } 
                }
            }
            return false;
        } else if(type == "diagonalUp"){
            for(int j = 0; j < 31; j++){
                fullSet = getDiagonalUp(j);
            
                if(j <= 15){
                    rowIdx = j+4;
                    columnIdx = 0;
                } else {
                    rowIdx = 19;
                    columnIdx = j-15;
                }

                for(int i = 0; i < fullSet.length-4; i++){ 
                    if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario O-O-O
                        int[][] spots = {{rowIdx-(i+1), columnIdx+i+1}, {rowIdx-(i+3), columnIdx+i+3}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } else if(fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario OOO--
                        this.freeSpace[0] = rowIdx-(i+3);
                        this.freeSpace[1] = columnIdx+i+3;
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario -OOO-
                        if(i == 0){
                            this.freeSpace[0] = rowIdx-(i+4);
                            this.freeSpace[1] = columnIdx+i+4;
                        } else if(i == fullSet.length-5){
                            this.freeSpace[0] = rowIdx-i;
                            this.freeSpace[1] = columnIdx+i;
                        } else {
                            int[][] spots = {{rowIdx-i, columnIdx+i}, {rowIdx-(i+4), columnIdx+i+4}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        }
                    } else if(fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece){ // check senario --OOO
                        this.freeSpace[0] = rowIdx-(i+1);
                        this.freeSpace[1] = columnIdx+i+1;
                        return true;
                    } else if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario O-OO-
                        this.freeSpace[0] = rowIdx-(i+1);
                        this.freeSpace[1] = columnIdx+i+1;
                        return true;
                    } else if(fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario OO-O-
                        this.freeSpace[0] = rowIdx-(i+2);
                        this.freeSpace[1] = columnIdx+i+2;
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece){ // check senario -O-OO,
                        this.freeSpace[0] = rowIdx-(i+2);
                        this.freeSpace[1] = columnIdx+i+2;
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario -OO-O
                        this.freeSpace[0] = rowIdx-(i+3);
                        this.freeSpace[1] = columnIdx+i+3;
                        return true;
                    } else if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece){ // check senario O--OO
                        int[][] spots = {{rowIdx-(i+1), columnIdx+i+1}, {rowIdx-(i+2), columnIdx+i+2}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } else if(fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario OO--O
                        int[][] spots = {{rowIdx-(i+2), columnIdx+i+2}, {rowIdx-(i+3), columnIdx+i+3}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } 
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private boolean setOfThreeUnblockedInFive(String type, char piece){
        char[] fullSet;
        int rowIdx;
        int columnIdx;
        if(type == "row"){
            for(int j = 0; j < 20; j++){
                fullSet = getRow(j);
                this.freeSpace[0] = j;

                for(int i = 0; i < 16; i++){
                    if(piece == this.opponentPiece && i < fullSet.length-6 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.' && fullSet[i+5] == piece && fullSet[i+6] == '.'){ // check senario -O-O-O-
                        int[][] spots = {{this.freeSpace[0], i}, {this.freeSpace[0], i+2}, {this.freeSpace[0], i+4}, {this.freeSpace[0], i+6}};
                        this.freeSpace = pickOptimalSpot(spots, this.computerPiece);
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario --OOO-
                        if(i == fullSet.length-6){
                            this.freeSpace[1] = i+1;
                        } else {
                            int[][] spots = {{this.freeSpace[0], i+1}, {this.freeSpace[0], i+5}};
                            this.freeSpace = pickOptimalSpot(spots, this.computerPiece);
                        }
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.' && fullSet[i+5] == '.' ){ // check senario -OOO--
                        if(i == 0){
                            this.freeSpace[1] = i+4;
                        } else {
                            int[][] spots = {{this.freeSpace[0], i}, {this.freeSpace[0], i+4}};
                            this.freeSpace = pickOptimalSpot(spots, this.computerPiece); 
                        }
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario -O-OO-
                        this.freeSpace[1] = i+2;
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario -OO-O-
                        this.freeSpace[1] = i+3;
                        return true;
                    } else if(piece == this.opponentPiece && i < fullSet.length-5 && fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario O--OO-,
                        int[][] spots = {{this.freeSpace[0], i+1}, {this.freeSpace[0], i+2}};
                        this.freeSpace = pickOptimalSpot(spots, this.computerPiece);
                        return true;
                    } else if(piece == this.opponentPiece && i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.' && fullSet[i+5] == piece){ // check senario -OO--O
                        int[][] spots = {{this.freeSpace[0], i+3}, {this.freeSpace[0], i+4}};
                        this.freeSpace = pickOptimalSpot(spots, this.computerPiece);
                        return true;
                    } 
                }
            }
            return false;
        } else if(type == "column"){
            for(int j = 0; j < 20; j++){
                fullSet = getColumn(j);
                this.freeSpace[1] = j;

                for(int i = 0; i < 16; i++){
                    if(piece == this.opponentPiece && i < fullSet.length-6 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.' && fullSet[i+5] == piece && fullSet[i+6] == '.'){ // check senario -O-O-O-
                        int[][] spots = {{i, this.freeSpace[1]}, {i+2, this.freeSpace[1]}, {i+4, this.freeSpace[1]}, {i+6, this.freeSpace[1]}};
                        this.freeSpace = pickOptimalSpot(spots, this.computerPiece);
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario --OOO-
                        if(i == fullSet.length-6){
                            this.freeSpace[0] = i+1;
                        } else {
                            int[][] spots = {{i+1, this.freeSpace[1]}, {i+5, this.freeSpace[1]}};
                            this.freeSpace = pickOptimalSpot(spots, this.computerPiece);
                        }
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.' && fullSet[i+5] == '.' ){ // check senario -OOO--
                        if(i == 0){
                            this.freeSpace[0] = i+4;
                        } else {
                            int[][] spots = {{i, this.freeSpace[1]}, {i+4, this.freeSpace[1]}};
                            this.freeSpace = pickOptimalSpot(spots, this.computerPiece);
                        }
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario -O-OO-
                        this.freeSpace[0] = i+2;
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario -OO-O-
                        this.freeSpace[0] = i+3;
                        return true;
                    } else if(piece == this.opponentPiece && i < fullSet.length-5 && fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario O--OO-
                        int[][] spots = {{i+1, this.freeSpace[1]}, {i+2, this.freeSpace[1]}};
                        this.freeSpace = pickOptimalSpot(spots, this.computerPiece);
                        return true;
                    } else if(piece == this.opponentPiece && i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.' && fullSet[i+5] == piece){ // check senario -OO--O
                        int[][] spots = {{i+3, this.freeSpace[1]}, {i+4, this.freeSpace[1]}};
                        this.freeSpace = pickOptimalSpot(spots, this.computerPiece);
                        return true;
                    } 
                }
            } 
            return false;   
        } else if(type == "diagonalDown"){ 
            for(int j = 0; j < 31; j++){
                fullSet = getDiagonalDown(j);
            
                if(j <= 15){
                    rowIdx = 15-j;
                    columnIdx = 0;
                } else {
                    rowIdx = 0;
                    columnIdx = j-15;
                }

                for(int i = 0; i < fullSet.length-4; i++){ 
                    if(piece == this.opponentPiece && i < fullSet.length-6 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.' && fullSet[i+5] == piece && fullSet[i+6] == '.'){ // check senario -O-O-O-
                        int[][] spots = {{rowIdx+i, columnIdx+i}, {rowIdx+i+2, columnIdx+i+2}, {rowIdx+i+4, columnIdx+i+4}, {rowIdx+i+6, columnIdx+i+6}};
                        this.freeSpace = pickOptimalSpot(spots, this.computerPiece);
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario --OOO-
                        if(i == fullSet.length-6){
                            this.freeSpace[0] = rowIdx+i+1;
                            this.freeSpace[1] = columnIdx+i+1;
                        } else {
                            int[][] spots = {{rowIdx+i+1, columnIdx+i+1}, {rowIdx+i+5, columnIdx+i+5}};
                            this.freeSpace = pickOptimalSpot(spots, this.computerPiece);
                        }
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.' && fullSet[i+5] == '.' ){ // check senario -OOO--
                        if(i == 0){
                            this.freeSpace[0] = rowIdx+i+4;
                            this.freeSpace[1] = columnIdx+i+4;
                        } else {
                            int[][] spots = {{rowIdx+i, columnIdx+i}, {rowIdx+i+4, columnIdx+i+4}};
                            this.freeSpace = pickOptimalSpot(spots, this.computerPiece);
                        }
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario -O-OO-
                        this.freeSpace[0] = rowIdx+i+2;
                        this.freeSpace[1] = columnIdx+i+2;
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario -OO-O-
                        this.freeSpace[0] = rowIdx+i+3;
                        this.freeSpace[1] = columnIdx+i+3;
                        return true;
                    } else if(piece == this.opponentPiece && i < fullSet.length-5 && fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario O--OO-
                        int[][] spots = {{rowIdx+i+1, columnIdx+i+1}, {rowIdx+i+2, columnIdx+i+2}};
                        this.freeSpace = pickOptimalSpot(spots, this.computerPiece);
                        return true;
                    } else if(piece == this.opponentPiece && i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.' && fullSet[i+5] == piece){ // check senario -OO--O
                        int[][] spots = {{rowIdx+i+3, columnIdx+i+3}, {rowIdx+i+4, columnIdx+i+4}};
                        this.freeSpace = pickOptimalSpot(spots, this.computerPiece);
                        return true;
                    } 
                }
            }
            return false;
        } else if(type == "diagonalUp"){
            for(int j = 0; j < 31; j++){
                fullSet = getDiagonalUp(j);
            
                if(j <= 15){
                    rowIdx = j+4;
                    columnIdx = 0;
                } else {
                    rowIdx = 19;
                    columnIdx = j-15;
                }

                for(int i = 0; i < fullSet.length-4; i++){ 
                    if(piece == this.opponentPiece && i < fullSet.length-6 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.' && fullSet[i+5] == piece && fullSet[i+6] == '.'){ // check senario -O-O-O-
                        int[][] spots = {{rowIdx-i, columnIdx+i}, {rowIdx-(i+2), columnIdx+i+2}, {rowIdx-(i+4), columnIdx+i+4}, {rowIdx-(i+6), columnIdx+i+6}};
                        this.freeSpace = pickOptimalSpot(spots, this.computerPiece);
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario --OOO-
                        if(i == fullSet.length-6){
                            this.freeSpace[0] = rowIdx-(i+1);
                            this.freeSpace[1] = columnIdx+i+1;
                        } else {
                            int[][] spots = {{rowIdx-(i+1), columnIdx+i+1}, {rowIdx-(i+5), columnIdx+i+5}};
                            this.freeSpace = pickOptimalSpot(spots, this.computerPiece);
                        }
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.' && fullSet[i+5] == '.' ){ // check senario -OOO--
                        if(i == 0){
                            this.freeSpace[0] = rowIdx-(i+4);
                            this.freeSpace[1] = columnIdx+i+4;
                        } else {
                            int[][] spots = {{rowIdx-i, columnIdx+i}, {rowIdx-(i+4), columnIdx+i+4}};
                            this.freeSpace = pickOptimalSpot(spots, this.computerPiece);
                        }
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario -O-OO-
                        this.freeSpace[0] = rowIdx-(i+2);
                        this.freeSpace[1] = columnIdx+i+2;
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario -OO-O-
                        this.freeSpace[0] = rowIdx-(i+3);
                        this.freeSpace[1] = columnIdx+i+3;
                        return true;
                    } else if(piece == this.opponentPiece && i < fullSet.length-5 && fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario O--OO-
                        int[][] spots = {{rowIdx-(i+1), columnIdx+i+1}, {rowIdx-(i+2), columnIdx+i+2}};
                        this.freeSpace = pickOptimalSpot(spots, this.computerPiece);
                        return true;
                    } else if(piece == this.opponentPiece && i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.' && fullSet[i+5] == piece){ // check senario -OO--O
                        int[][] spots = {{rowIdx-(i+3), columnIdx+i+3}, {rowIdx-(i+4), columnIdx+i+4}};
                        this.freeSpace = pickOptimalSpot(spots, this.computerPiece);
                        return true;
                    } 
                }
            }
            return false;
        } else {
            return false;
        }
    }

        private boolean setOfTwoInFive(String type, char piece){
            char[] fullSet;
            int rowIdx;
            int columnIdx;
            if(type == "row"){  
                for(int j = 0; j < 20; j++){
                    fullSet = getRow(j);
                    this.freeSpace[0] = j;
    
                    for(int i = 0; i < 16; i++){
                        if(i > 0 && fullSet[i-1] == '.' && fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario OO---, making sure the left is not blocked
                            this.freeSpace[1] = i+2;
                            return true;
                        } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario -O-O-
                            this.freeSpace[1] = i+2;
                            return true;
                        } else if(i > 0 && fullSet[i-1] == '.' && fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario O-O--, making sure the left is not blocked
                            this.freeSpace[1] = i+1;
                            return true;
                        } else if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario O--O-
                            int[][] spots = {{this.freeSpace[0], i+1}, {this.freeSpace[0], i+2}, {this.freeSpace[0], i+4}};
                            this.freeSpace = pickOptimalSpot(spots, piece); 
                            return true;
                        } else if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario O---O
                            int[][] spots = {{this.freeSpace[0], i+1}, {this.freeSpace[0], i+2}, {this.freeSpace[0], i+3}};
                            this.freeSpace = pickOptimalSpot(spots, piece); 
                            return true;
                        } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario -OO--
                            int[][] spots = {{this.freeSpace[0], i}, {this.freeSpace[0], i+3}};
                            this.freeSpace = pickOptimalSpot(spots, piece); 
                            return true;
                        } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario -O--O
                            int[][] spots = {{this.freeSpace[0], i}, {this.freeSpace[0], i+2}, {this.freeSpace[0], i+3}};
                            this.freeSpace = pickOptimalSpot(spots, piece); 
                            return true;
                        } else if(fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario --OO-
                            int[][] spots = {{this.freeSpace[0], i+1}, {this.freeSpace[0], i+4}};
                            this.freeSpace = pickOptimalSpot(spots, piece); 
                            return true;
                        } else if(fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario --O-O
                            this.freeSpace[1] = i+3;
                            return true;
                        } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario ---OO, making sure the right is not blocked
                            this.freeSpace[1] = i+2;
                            return true;
                        }
                    }
                }
                return false;
            } else if(type == "column"){ 
                for(int j = 0; j < 20; j++){
                    fullSet = getColumn(j);
                    this.freeSpace[1] = j;
    
                    for(int i = 0; i < 16; i++){
                        if(i > 0 && fullSet[i-1] == '.' && fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario OO---, making sure the left is not blocked
                            this.freeSpace[0] = i+2;
                            return true;
                        } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario -O-O-
                            this.freeSpace[0] = i+2;
                            return true;
                        } else if(i > 0 && fullSet[i-1] == '.' && fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario O-O--, making sure the left is not blocked
                            this.freeSpace[0] = i+1;
                            return true;
                        } else if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario O--O-
                            int[][] spots = {{i+1, this.freeSpace[1]}, {i+2, this.freeSpace[1]}, {i+4, this.freeSpace[1]}};
                            this.freeSpace = pickOptimalSpot(spots, piece); 
                            return true;
                        } else if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario O---O
                            int[][] spots = {{i+1, this.freeSpace[1]}, {i+2, this.freeSpace[1]}, {i+3, this.freeSpace[1]}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario -OO--
                            int[][] spots = {{i, this.freeSpace[1]}, {i+3, this.freeSpace[1]}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario -O--O
                            int[][] spots = {{i, this.freeSpace[1]}, {i+2, this.freeSpace[1]}, {i+3, this.freeSpace[1]}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        } else if(fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario --OO-
                            int[][] spots = {{i+1, this.freeSpace[1]}, {i+4, this.freeSpace[1]}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario --O-O, making sure the right is not blocked
                            this.freeSpace[0] = i+3;
                            return true;
                        } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario ---OO, making sure the right is not blocked
                            this.freeSpace[0] = i+2;
                            return true;
                        } 
                    }
                } 
                return false;   
            } else if(type == "diagonalDown"){
                for(int j = 0; j < 31; j++){
                    fullSet = getDiagonalDown(j);
                
                    if(j <= 15){
                        rowIdx = 15-j;
                        columnIdx = 0;
                    } else {
                        rowIdx = 0;
                        columnIdx = j-15;
                    }
    
                    for(int i = 0; i < fullSet.length-4; i++){ 
                        if(i > 0 && fullSet[i-1] == '.' && fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario OO---, making sure the left is not blocked
                            this.freeSpace[0] = rowIdx+i+2;
                            this.freeSpace[1] = columnIdx+i+2;
                            return true;
                        } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario -O-O-
                            this.freeSpace[0] = rowIdx+i+2;
                            this.freeSpace[1] = columnIdx+i+2;
                            return true;
                        } else if(i > 0 && fullSet[i-1] == '.' && fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario O-O--, making sure the left is not blocked
                            this.freeSpace[0] = rowIdx+i+1;
                            this.freeSpace[1] = columnIdx+i+1;
                            return true;
                        } else if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario O--O-
                            int[][] spots = {{rowIdx+i+1, columnIdx+i+1}, {rowIdx+i+2, columnIdx+i+2}, {rowIdx+i+4, columnIdx+i+4}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        } else if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario O---O
                            int[][] spots = {{rowIdx+i+1, columnIdx+i+1}, {rowIdx+i+2, columnIdx+i+2}, {rowIdx+i+3, columnIdx+i+3}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario -OO--
                            int[][] spots = {{rowIdx+i, columnIdx+i}, {rowIdx+i+3, columnIdx+i+3}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario -O--O
                            int[][] spots = {{rowIdx+i, columnIdx+i}, {rowIdx+i+2, columnIdx+i+2}, {rowIdx+i+3, columnIdx+i+3}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        } else if(fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario --OO-
                            int[][] spots = {{rowIdx+i+1, columnIdx+i+1}, {rowIdx+i+4, columnIdx+i+4}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario --O-O, making sure the right is not blocked
                            this.freeSpace[0] = rowIdx+i+3;
                            this.freeSpace[1] = columnIdx+i+3;
                            return true;
                        } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario ---OO, making sure the right is not blocked
                            this.freeSpace[0] = rowIdx+i+2;
                            this.freeSpace[1] = columnIdx+i+2;
                            return true;
                        } 
                    }
                }
                return false;
            } else if(type == "diagonalUp"){ 
                for(int j = 0; j < 31; j++){
                    fullSet = getDiagonalUp(j);
                
                    if(j <= 15){
                        rowIdx = j+4;
                        columnIdx = 0;
                    } else {
                        rowIdx = 19;
                        columnIdx = j-15;
                    }
    
                    for(int i = 0; i < fullSet.length-4; i++){ 
                        if(i > 0 && fullSet[i-1] == '.' && fullSet[i] == piece && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario OO---, making sure the left is not blocked
                            this.freeSpace[0] = rowIdx-(i+2);
                            this.freeSpace[1] = columnIdx+i+2;
                            return true;
                        } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario -O-O-
                            this.freeSpace[0] = rowIdx-(i+2);
                            this.freeSpace[1] = columnIdx+i+2;
                            return true;
                        } else if(i > 0 && fullSet[i-1] == '.' && fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario O-O--, checking that the left is not blocked
                            this.freeSpace[0] = rowIdx-(i+1);
                            this.freeSpace[1] = columnIdx+i+1;
                            return true;
                        } else if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario O--O-
                            int[][] spots = {{rowIdx-(i+1), columnIdx+i+1}, {rowIdx-(i+2), columnIdx+i+2}, {rowIdx-(i+4), columnIdx+i+4}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        } else if(fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario O---O
                            int[][] spots = {{rowIdx-(i+1), columnIdx+i+1}, {rowIdx-(i+2), columnIdx+i+2}, {rowIdx-(i+3), columnIdx+i+3}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario -OO--
                            int[][] spots = {{rowIdx-i, columnIdx+i}, {rowIdx-(i+3), columnIdx+i+3}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == piece){ // check senario -O--O
                            int[][] spots = {{rowIdx-i, columnIdx+i}, {rowIdx-(i+2), columnIdx+i+2}, {rowIdx-(i+3), columnIdx+i+3}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        } else if(fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario --OO-
                            int[][] spots = {{rowIdx-(i+1), columnIdx+i+1}, {rowIdx-(i+4), columnIdx+i+4}};
                            this.freeSpace = pickOptimalSpot(spots, piece);
                            return true;
                        } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario --O-O, making sure the right is not blocked
                            this.freeSpace[0] = rowIdx-(i+3);
                            this.freeSpace[1] = columnIdx+i+3;
                            return true;
                        } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario ---OO, making sure the right is not blocked
                            this.freeSpace[0] = rowIdx-(i+2);
                            this.freeSpace[1] = columnIdx+i+2;
                            return true;
                        } 
                    }
                }
                return false;
            } else {
                return false;
            }
        }
    
        private boolean setOfOneInFive(String type, char piece){ 
        char[] fullSet;
        int rowIdx;
        int columnIdx;
        if(type == "row"){  
            for(int j = 0; j < 20; j++){
                fullSet = getRow(j);
                this.freeSpace[0] = j;

                for(int i = 0; i < 16; i++){
                    if(i > 0 && fullSet[i-1] == '.' && fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario O----, making sure the left is not blocked
                        this.freeSpace[1] = i+1;
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario -O---
                        int[][] spots = {{this.freeSpace[0], i}, {this.freeSpace[0], i+2}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario --O--
                        int[][] spots = {{this.freeSpace[0], i+1}, {this.freeSpace[0], i+3}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario ---O-
                        int[][] spots = {{this.freeSpace[0], i+2}, {this.freeSpace[0], i+4}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario ----O, making sure the right is not blocked
                        this.freeSpace[1] = i+3;
                        return true;
                    } 
                }
            }
            return false;
        } else if(type == "column"){ 
            for(int j = 0; j < 20; j++){
                fullSet = getColumn(j);
                this.freeSpace[1] = j;

                for(int i = 0; i < 16; i++){
                    if(i > 0 && fullSet[i-1] == '.' && fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario O----, making sure the left is not blocked
                        this.freeSpace[0] = i+1;
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario -O---
                        int[][] spots = {{i, this.freeSpace[1]}, {i+2, this.freeSpace[1]}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario --O--
                        int[][] spots = {{i+1, this.freeSpace[1]}, {i+3, this.freeSpace[1]}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario ---O-
                        int[][] spots = {{i+2, this.freeSpace[1]}, {i+4, this.freeSpace[1]}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario ----O, making sure the right is not blocked
                        this.freeSpace[0] = i+3;
                        return true;
                    }  
                }
            } 
            return false;   
        } else if(type == "diagonalDown"){
            for(int j = 0; j < 31; j++){
                fullSet = getDiagonalDown(j);
            
                if(j <= 15){
                    rowIdx = 15-j;
                    columnIdx = 0;
                } else {
                    rowIdx = 0;
                    columnIdx = j-15;
                }

                for(int i = 0; i < fullSet.length-4; i++){ 
                    if(i > 0 && fullSet[i-1] == '.' && fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario O----, making sure the left is not blocked
                        this.freeSpace[0] = rowIdx+i+1;
                        this.freeSpace[1] = columnIdx+i+1;
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario -O---
                        int[][] spots = {{rowIdx+i, columnIdx+i}, {rowIdx+i+2, columnIdx+i+2}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario --O--
                        int[][] spots = {{rowIdx+i+1, columnIdx+i+1}, {rowIdx+i+3, columnIdx+i+3}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario ---O-
                        int[][] spots = {{rowIdx+i+4, columnIdx+i+4}, {rowIdx+i+2, columnIdx+i+2}};
                        this.freeSpace = pickOptimalSpot(spots, piece); 
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario ----O, making sure the right is not blocked
                        this.freeSpace[0] = rowIdx+i+3;
                        this.freeSpace[1] = columnIdx+i+3;
                        return true;
                    }  
                }
            }
            return false;
        } else if(type == "diagonalUp"){ 
            for(int j = 0; j < 31; j++){
                fullSet = getDiagonalUp(j);
            
                if(j <= 15){
                    rowIdx = j+4;
                    columnIdx = 0;
                } else {
                    rowIdx = 19;
                    columnIdx = j-15;
                }

                for(int i = 0; i < fullSet.length-4; i++){ 
                    if(i > 0 && fullSet[i-1] == '.' && fullSet[i] == piece && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario O----, making sure the left is not blocked
                        this.freeSpace[0] = rowIdx-(i+1);
                        this.freeSpace[1] = columnIdx+i+1;
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == piece && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario -O---
                        int[][] spots = {{rowIdx-i, columnIdx+i}, {rowIdx-(i+2), columnIdx+i+2}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == piece && fullSet[i+3] == '.' && fullSet[i+4] == '.'){ // check senario --O--
                        int[][] spots = {{rowIdx-(i+1), columnIdx+i+1}, {rowIdx-(i+3), columnIdx+i+3}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } else if(fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == piece && fullSet[i+4] == '.'){ // check senario ---O-
                        int[][] spots = {{rowIdx-(i+2), columnIdx+i+2}, {rowIdx-(i+4), columnIdx+i+4}};
                        this.freeSpace = pickOptimalSpot(spots, piece);
                        return true;
                    } else if(i < fullSet.length-5 && fullSet[i] == '.' && fullSet[i+1] == '.' && fullSet[i+2] == '.' && fullSet[i+3] == '.' && fullSet[i+4] == piece && fullSet[i+5] == '.'){ // check senario ----O, making sure the right is not blocked
                        this.freeSpace[0] = rowIdx-(i+3);
                        this.freeSpace[1] = columnIdx+i+3;
                        return true;
                    }  
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private boolean isEmptyFivebyFive(int row, int column){
        if(this.board[row][column] != '.'){ // check the space
            return false;
        } else if(this.board[row-2][column-2] != '.'){
            return false;
        } else if(this.board[row-1][column-2] != '.'){
            return false;
        } else if(this.board[row][column-2] != '.'){
            return false;
        } else if(this.board[row+1][column-2] != '.'){
            return false;
        } else if(this.board[row+2][column-2] != '.'){
            return false;
        } else if(this.board[row-2][column-1] != '.'){
            return false;
        } else if(this.board[row-1][column-1] != '.'){
            return false;
        } else if(this.board[row][column-1] != '.'){
            return false;
        } else if(this.board[row+1][column-1] != '.'){
            return false;
        } else if(this.board[row+2][column-1] != '.'){
            return false;
        } else if(this.board[row-2][column] != '.'){
            return false;
        } else if(this.board[row-1][column] != '.'){
            return false;
        } else if(this.board[row+1][column] != '.'){
            return false;
        } else if(this.board[row+2][column] != '.'){
            return false;
        } else if(this.board[row-2][column+1] != '.'){
            return false;
        } else if(this.board[row-1][column+1] != '.'){
            return false;
        } else if(this.board[row][column+1] != '.'){
            return false;
        } else if(this.board[row+1][column+1] != '.'){
            return false;
        } else if(this.board[row+2][column+1] != '.'){
            return false;
        } else if(this.board[row-2][column+2] != '.'){
            return false;
        } else if(this.board[row-1][column+2] != '.'){
            return false;
        } else if(this.board[row][column+2] != '.'){
            return false;
        } else if(this.board[row+1][column+2] != '.'){
            return false;
        } else if(this.board[row+2][column+2] != '.'){
            return false;
        } else {
            this.freeSpace[0] = row;
            this.freeSpace[1] = column;
            return true;
        }
    }
    
    private boolean isEmptySetOfFive(int row, int column){ 
        if(this.board[row][column] != '.'){
            return false;
        } else if(row < 16 && this.board[row+1][column] == '.' && this.board[row+2][column] == '.' && this.board[row+3][column] == '.' && this.board[row+4][column] == '.'){ // row: checking for four empty spots after the piece
            return true;
        } else if(column < 16 && this.board[row][column+1] == '.' && this.board[row][column+2] == '.' && this.board[row][column+3] == '.' && this.board[row][column+4] == '.'){ // column: checking for four empty spots after the piece
            return true;
        } else if(row < 16 && column < 16 && this.board[row+1][column+1] == '.' && this.board[row+2][column+2] == '.' && this.board[row+3][column+3] == '.' && this.board[row+4][column+4] == '.'){ // diagonalUp: checking for four empty spots after the piece
            return true;
        } else if(row > 3 && column < 16 && this.board[row-1][column+1] == '.' && this.board[row-2][column+2] == '.' && this.board[row-3][column+3] == '.' && this.board[row-4][column+4] == '.'){ // diagonalDown: checking for four empty spots after the piece
            return true;
        } else if(row > 0 && row < 17 && this.board[row-1][column] == '.' && this.board[row+1][column] == '.' && this.board[row+2][column] == '.' && this.board[row+3][column] == '.'){ // row: checking for one empty spot before and three empty spots after the piece
            return true;
        } else if(column > 0 && column < 17 && this.board[row][column-1] == '.' && this.board[row][column+1] == '.' && this.board[row][column+2] == '.' && this.board[row][column+3] == '.'){ // column: checking for one empty spot before and three empty spots after the piece
            return true;
        } else if(row > 0 && row < 17 && column > 0 && column < 17 && this.board[row-1][column-1] == '.' && this.board[row+1][column+1] == '.' && this.board[row+2][column+2] == '.' && this.board[row+3][column+3] == '.'){ // diagonalUp: checking one empty spot before and three empty spots after the piece
            return true;
        } else if(row > 2 && row < 17 && column > 0 && column < 17 && this.board[row+1][column-1] == '.' && this.board[row-1][column+1] == '.' && this.board[row-2][column+2] == '.' && this.board[row-3][column+3] == '.'){ // diagonalDown: checking one empty spot before and three empty spots after the piece
            return true;
        } else if(row > 1 && row < 18 && this.board[row-2][column] == '.' && this.board[row-1][column] == '.' && this.board[row+1][column] == '.' && this.board[row+2][column] == '.'){ // row: checking for two empty spots before and two empty spots after the piece
            return true;
        } else if(column > 1 && column < 18 && this.board[row][column-2] == '.' && this.board[row][column-1] == '.' && this.board[row][column+1] == '.' && this.board[row][column+2] == '.'){ // column: checking for two empty spots before and two empty spots after the piece
            return true;
        } else if(row > 1 && row < 18 && column > 1 && column < 18 && this.board[row-2][column-2] == '.' && this.board[row-1][column-1] == '.' && this.board[row+1][column+1] == '.' && this.board[row+2][column+2] == '.'){ // diagonalUp: checking for two empty spots before and two empty spots after the piece
            return true;
        } else if(row > 1 && row < 18 && column > 1 && column < 18 && this.board[row+2][column-2] == '.' && this.board[row+1][column-1] == '.' && this.board[row-1][column+1] == '.' && this.board[row-2][column+2] == '.'){ // diagonalDown: checking for two empty spots before and two empty spots after the piece
            return true;
        } else if(row > 2 && row < 19 && this.board[row-3][column] == '.' && this.board[row-2][column] == '.' && this.board[row-1][column] == '.' && this.board[row+1][column] == '.'){ // row: checking for three empty spots before and one empty spot after the piece
            return true;
        } else if(column > 2 && column < 19 && this.board[row][column-3] == '.' && this.board[row][column-2] == '.' && this.board[row][column-1] == '.' && this.board[row][column+1] == '.'){ // column: checking for three empty spots before and one empty spot after the piece
            return true;
        } else if(row > 2 && row < 19 && column > 2 && column < 19 && this.board[row-3][column-3] == '.' && this.board[row-2][column-2] == '.' && this.board[row-1][column-1] == '.' && this.board[row+1][column+1] == '.'){ // diagonalUp: checking for three empty spots before and one empty spot after the piece
            return true;
        } else if(row > 0 && row < 17 && column > 2 && column < 19 && this.board[row+3][column-3] == '.' && this.board[row+2][column-2] == '.' && this.board[row+1][column-1] == '.' && this.board[row-1][column+1] == '.'){ // diagonalDown: checking for three empty spots before and one empty spot after the piece
            return true;
        } else if(row > 3 && this.board[row-4][column] == '.' && this.board[row-3][column] == '.' && this.board[row-2][column] == '.' && this.board[row-1][column] == '.'){ // row: checking for four empty spots before the piece
            return true;
        } else if(column > 3 && this.board[row][column-4] == '.' && this.board[row][column-3] == '.' && this.board[row][column-2] == '.' && this.board[row][column-1] == '.'){ // column: checking for four empty spots before the piece the piece
            return true;
        } else if(row > 3 && column > 3 && this.board[row-4][column-4] == '.' && this.board[row-3][column-3] == '.' && this.board[row-2][column-2] == '.' && this.board[row-1][column-1] == '.'){ // diagonalUp: checking for four empty spots before the piece
            return true;
        } else if(row < 16 && column > 3 && this.board[row+4][column-4] == '.' && this.board[row+3][column-3] == '.' && this.board[row+2][column-2] == '.' && this.board[row+1][column-1] == '.'){ // diagonalDown: checking for four empty spots before the piece
            return true;
        } else {
            return false;
        }
    }

    private boolean boardHasNPieces(int n){
        int piecesOnBoard = 0;
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                if(this.board[i][j] != '.') piecesOnBoard++;
            }
        }
        if(piecesOnBoard > n) return false;
        return true;
    }

    private boolean nNorth(int n, int rowIdx, int columnIdx, char piece){
        for(int i = 1; i <= n; i++){
            if(this.board[rowIdx-i][columnIdx] != piece) return false;
        }
        if(this.board[rowIdx-(n+1)][columnIdx] != '.') return false;
        
        return true;
    }

    private boolean nSouth(int n, int rowIdx, int columnIdx, char piece){
        for(int i = 1; i <= n; i++){
            if(this.board[rowIdx+i][columnIdx] != piece) return false;
        }
        if(this.board[rowIdx+(n+1)][columnIdx] != '.') return false;
        
        return true;
    }

    private boolean nEast(int n, int rowIdx, int columnIdx, char piece){
        for(int i = 1; i <= n; i++){
            if(this.board[rowIdx][columnIdx+i] != piece) return false;
        }
        if(this.board[rowIdx][columnIdx+(n+1)] != '.') return false;
        
        return true;
    }

    private boolean nWest(int n, int rowIdx, int columnIdx, char piece){
        for(int i = 1; i <= n; i++){
            if(this.board[rowIdx][columnIdx-i] != piece) return false;
        }
        if(this.board[rowIdx][columnIdx-(n+1)] != '.') return false;
        
        return true;
    }

    private boolean nNorthEast(int n, int rowIdx, int columnIdx, char piece){
        for(int i = 1; i <= n; i++){
            if(this.board[rowIdx-i][columnIdx+i] != piece) return false;
        }
        if(this.board[rowIdx-(n+1)][columnIdx+(n+1)] != '.') return false;

        return true;
    }
    
    private boolean nNorthWest(int n, int rowIdx, int columnIdx, char piece){
        for(int i = 1; i <= n; i++){
            if(this.board[rowIdx-i][columnIdx-i] != piece) return false;
        }
        if(this.board[rowIdx-(n+1)][columnIdx-(n+1)] != '.') return false;
        
        return true;
    }
    
    private boolean nSouthEast(int n, int rowIdx, int columnIdx, char piece){
        for(int i = 1; i <= n; i++){
            if(this.board[rowIdx+i][columnIdx+i] != piece) return false;
        }
        if(this.board[rowIdx+(n+1)][columnIdx+(n+1)] != '.') return false;
        
        return true;
    }
    
    private boolean nSouthWest(int n, int rowIdx, int columnIdx, char piece){
        for(int i = 1; i <= n; i++){
            if(this.board[rowIdx+i][columnIdx-i] != piece) return false;
        }
        if(this.board[rowIdx+(n+1)][columnIdx-(n+1)] != '.') return false;
        
        return true;
    }

    private int[] pickOptimalSpot(int[][] spots, char piece){
        int bestScore = score(spots[0], piece);
        int[] bestSpot = spots[0];

        for(int i = 1; i < spots.length; i++){
            int newScore = score(spots[i], piece);
            if(bestScore < newScore || this.random.nextInt(2) == 1){
                bestScore = newScore;
                bestSpot = spots[i];
            } 
        }

        return bestSpot;
    }

    private int score(int[] spot, char piece){
        char opposingPiece;
        if(piece == 'X'){
            opposingPiece = 'O';
        } else {
            opposingPiece = 'X';
        }
        int score = 0; // the score is the number of piece around that spot
        int rowIdx = spot[0];
        int columnIdx = spot[1];

        //check north
        int r = rowIdx;
        int c = columnIdx;
        while(r >= 0){
            if(this.board[r][c] == piece){
                score++;
            } else if(this.board[r][c] == opposingPiece){
                break;
            }
            r--;
        }
        //check north east
        r = rowIdx;
        c = columnIdx;
        while(r >= 0 && c < 20){
            if(this.board[r][c] == piece){
                score++;
            } else if(this.board[r][c] == opposingPiece){
                break;
            }
            r--;
            c++;
        }
        //check east
        r = rowIdx;
        c = columnIdx;
        while(c < 20){
            if(this.board[r][c] == piece){
                score++;
            } else if(this.board[r][c] == opposingPiece){
                break;
            }
            c++;
        }
        //check south east
        r = rowIdx;
        c = columnIdx;
        while(r < 20 && c < 20){
            if(this.board[r][c] == piece){
                score++;
            } else if(this.board[r][c] == opposingPiece){
                break;
            }
            r++;
            c++;
        }
        //check south
        r = rowIdx;
        c = columnIdx;
        while(r < 20){
            if(this.board[r][c] == piece){
                score++;
            } else if(this.board[r][c] == opposingPiece){
                break;
            }
            r++;
        }
        //check south west
        r = rowIdx;
        c = columnIdx;
        while(r < 20 && c >= 0){
            if(this.board[r][c] == piece){
                score++;
            } else if(this.board[r][c] == opposingPiece){
                break;
            }
            r++;
            c--;
        }
        //check west
        r = rowIdx;
        c = columnIdx;
        while(c >= 0){
            if(this.board[r][c] == piece){
                score++;
            } else if(this.board[r][c] == opposingPiece){
                break;
            }
            c--;
        }
        //check north west
        r = rowIdx;
        c = columnIdx;
        while(r >= 0 && c >= 0){
            if(this.board[r][c] == piece){
                score++;
            } else if(this.board[r][c] == opposingPiece){
                break;
            }
            r--;
            c--;
        }

        return score;
    }

    private boolean fullBoard(){
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                if(this.board[i][j] == '.') return false;
            }
        }
        return true;
    }

    private int determineLongWinner(){
        int setsOfFiveX = 0;
        int setsOfFiveO = 0;

        // check rows for X and O
        double numAdjacentX = 0;
        double numAdjacentO = 0;
        char[] row;
        for(int i = 0; i < 20; i++){
            row = getRow(i);
            for(int j = 0; j < 20; j++){
                if(row[j] == 'O'){
                    if(numAdjacentX >= 5){
                        setsOfFiveX += Math.floor((numAdjacentX / 5));
                    }
                    numAdjacentX = 0;
                    numAdjacentO++;
                } else if(row[j] == 'X'){
                    if(numAdjacentO >= 5){
                        setsOfFiveO += Math.floor((numAdjacentO / 5));
                    }
                    numAdjacentX++;
                    numAdjacentO = 0;
                } 
            }
        }

        // check columns for X and O
        numAdjacentX = 0;
        numAdjacentO = 0;
        char[] column;
        for(int i = 0; i < 20; i++){
            column = getColumn(i);
            for(int j = 0; j < 20; j++){
                if(column[j] == 'O'){
                    if(numAdjacentX >= 5){
                        setsOfFiveX += Math.floor((numAdjacentX / 5));
                    }
                    numAdjacentX = 0;
                    numAdjacentO++;
                } else if(column[j] == 'X'){
                    if(numAdjacentO >= 5){
                        setsOfFiveO += Math.floor((numAdjacentO / 5));
                    }
                    numAdjacentX++;
                    numAdjacentO = 0;
                } 
            }
        }
        
        // check diagonalDown for X and O
        numAdjacentX = 0;
        numAdjacentO = 0;
        char[] diagonalDown;
        for(int i = 0; i < 30; i++){
            diagonalDown = getDiagonalDown(i);
            for(int j = 0; j < diagonalDown.length; j++){
                if(diagonalDown[j] == 'O'){
                    if(numAdjacentX >= 5){
                        setsOfFiveX += Math.floor((numAdjacentX / 5));
                    }
                    numAdjacentX = 0;
                    numAdjacentO++;
                } else if(diagonalDown[j] == 'X'){
                    if(numAdjacentO >= 5){
                        setsOfFiveO += Math.floor((numAdjacentO / 5));
                    }
                    numAdjacentX++;
                    numAdjacentO = 0;
                } 
            }
        }

        // check diagonalUp for X and O
        numAdjacentX = 0;
        numAdjacentO = 0;
        char[] diagonalUp;
        for(int i = 0; i < 30; i++){
            diagonalUp = getDiagonalUp(i);
            for(int j = 0; j < diagonalUp.length; j++){
                if(diagonalUp[j] == 'O'){
                    if(numAdjacentX >= 5){
                        setsOfFiveX += Math.floor((numAdjacentX / 5));
                    }
                    numAdjacentX = 0;
                    numAdjacentO++;
                } else if(diagonalUp[j] == 'X'){
                    if(numAdjacentO >= 5){
                        setsOfFiveO += Math.floor((numAdjacentO / 5));
                    }
                    numAdjacentX++;
                    numAdjacentO = 0;
                } 
            }
        }

        if(setsOfFiveX > setsOfFiveO){
            return 1;
        } else if(setsOfFiveX < setsOfFiveO){
            return 2;
        } else {
            return 0; // no one won 
        }
    }
}
