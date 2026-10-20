public class TTTBoard {
    private String[][] board;
    private static final int ROW = 3;
    private static final int COL = 3;

    public TTTBoard(){
        board = new String[ROW][COL];
        clearBoard();
    }

    public void clearBoard(){
        for(int r = 0; r < ROW; r++){
            for(int c = 0; c < COL; c++){
                board[r][c] = " ";
            }
        }
    }

    public boolean setTile(int r, int c, String symbol){
        if(board[r][c].equals(" ")){
            board[r][c] = symbol;
            return true;
        }
        return false;
    }

    public boolean isWin(String player){
        return isRowWin(player) || isColWin(player) ||isDiagonalWin(player);
    }

    private boolean isRowWin(String player){
        for(int r = 0; r < ROW; r++){
            if(board[r][0].equals(player) && board[r][1].equals(player) && board[r][2].equals(player)){
                return true;
            }
        }
        return false;
    }

    private boolean isColWin(String player){
        for(int c = 0; c < COL; c++){
            if(board[0][c].equals(player) && board[1][c].equals(player) && board[2][c].equals(player)){
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWin(String player) {
        if(board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)){
            return true;
        }
        return board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player);
    }

    public boolean isTie(){
        for(int r = 0; r < ROW; r++){
            for(int c = 0; c < COL; c++){
                if(board[r][c].equals(" ")){
                    return false;
                }
            }
        }
        return true;
    }

}
