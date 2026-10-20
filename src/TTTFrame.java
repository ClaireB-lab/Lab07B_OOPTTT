import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TTTFrame extends JFrame {
    private TTTBoard board;
    private TTTTileButton[][] buttons;
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private int moveCount;

    public TTTFrame() {
        super("OOP Tic Tac Toe");
        board = new TTTBoard();
        buttons = new TTTTileButton[3][3];
        playerX = new Player("X");
        playerO = new Player("O");
        currentPlayer = playerX;
        moveCount = 0;

        setLayout(new BorderLayout());
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                buttons[r][c] = new TTTTileButton(r, c);
                buttons[r][c].setFont(new Font("Arial", Font.BOLD, 40));
                buttons[r][c].setText(" ");
                buttons[r][c].addActionListener(e -> handleButtonClick(e));
                boardPanel.add(buttons[r][c]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);
        JPanel controlPanel = new JPanel();
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        controlPanel.add(quitButton);
        add(controlPanel, BorderLayout.SOUTH);

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleButtonClick(ActionEvent e) {
        TTTTileButton clicked = (TTTTileButton) e.getSource();
        int r = clicked.getRow();
        int c = clicked.getCol();

        if (!clicked.getText().equals(" ")) {
            JOptionPane.showMessageDialog(this, "Invalid move");
            return;
        }
        
        if (board.setTile(r, c, currentPlayer.getSymbol())) {
            clicked.setText(currentPlayer.getSymbol());
            moveCount++;

            if (moveCount >= 5 && board.isWin(currentPlayer.getSymbol())) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer.getSymbol() + " Wins!");
                promptPlayAgain();
            } else if (moveCount >= 7 && board.isTie()) {
                JOptionPane.showMessageDialog(this, "It's a tie!");
                promptPlayAgain();
            } else {
                currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
            }
        }
    }

    private void promptPlayAgain() {
        int choice = JOptionPane.showConfirmDialog(this, "Would you like to play another game?", "Play Again", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private void resetGame() {
        board.clearBoard();
        moveCount = 0;
        currentPlayer = playerX;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                buttons[r][c].setText(" ");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TTTFrame::new);
    }
}