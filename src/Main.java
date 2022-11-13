import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;

public class Main extends Application
{
    public static void main(String[] args) 
    {
        launch(args);
    }

    

    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        primaryStage.setHeight(900);
        primaryStage.setWidth(900);
        primaryStage.setTitle("hallo");

        int cellcount = 20;
        int cellsize = 40;
        int gridsize = cellcount*cellsize;
        Game game = new Game(cellcount,cellcount,0);
        Canvas canvas= new Canvas(gridsize,gridsize);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //gc.translate(gridsize / 2, gridsize / 2);
        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root,gridsize,gridsize);
        primaryStage.setScene(scene);
        primaryStage.show();
        int f=0;
        long framedelay = 1000000000;
        //framedelay*=10;
        new AnimationTimer() 
        {
            boolean f=true;
            public void handle(long now)
            {
                gc.clearRect(0,0,gridsize,gridsize);
                long framestart = System.nanoTime();
                gc.setFill(Color.rgb(0,0,0));
                gc.fillRect(0,0,gridsize,gridsize);
                int[][] grid = game.getState();
                for(int i=0;i<cellcount;i++)
                {
                    for(int j=0;j<cellcount;j++)
                    {
                        if(grid[i][j]==1)
                        {
                            gc.setFill(Color.rgb(0,100,255));
                            gc.fillRect(cellsize*j,cellsize*i,cellsize,cellsize);
                        }
                        if(grid[i][j]>1)
                        {
                            gc.setFill(Color.rgb(0,0,255));
                            gc.fillRect(cellsize*j,cellsize*i,cellsize,cellsize);
                        }
                        if(grid[i][j]==-1)
                        {
                            gc.setFill(Color.rgb(255,0,0));
                            gc.fillRect(cellsize*j,cellsize*i,cellsize,cellsize);
                        }
                    }
                }
                
                scene.setOnKeyPressed(e -> 
                    {
                     if (e.getCode() == KeyCode.W)f=game.update(0);
                     else if (e.getCode() == KeyCode.D)f=game.update(1);
                     else if (e.getCode() == KeyCode.S)f=game.update(2);
                     else if (e.getCode() == KeyCode.A)f=game.update(3);
                     else f=game.update(4);
                    });
                if(!f)primaryStage.close();
                
                //while(System.nanoTime()-framestart<framedelay);

            }
        }.start();
        
        System.out.println("exit");
        
    }
}
