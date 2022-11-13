import java.util.Random;

public class Game 
{
    int width;
    int height;
    int orientation;
    int length;
    Random rand;
    //0 - north
    //1 - east
    //2 - south
    //3 - west
    int[][] grid;

    public Game(int h,int w,int seed)
    {
        width = w;
        height = h;
        rand = new Random();
        grid = new int[h][w];
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                grid[i][j]=0;
            }
        }
        orientation = 0;
        length = 3;
        grid[h/2][w/2] = 1;//distance from head
        grid[h/2][w/2+1] = 2;
        grid[h/2][w/2+2] = 3;
        grid[3][5] = -1;
    }

    public boolean update(int o)
    {
        int xpos=0,ypos=0;
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                if(grid[i][j]==1)
                {
                    xpos=j;
                    ypos=i;
                    j=width;
                    i=height;
                }
            }
        }
        if(o!=4)if(orientation%2!=o%2)orientation=o;
        if(orientation==0)
        {
            ypos--;
            if(ypos<0)return false;
        }
        else if(orientation==1)
        {
            xpos++;
            if(xpos>=width)return false;
        }
        else if(orientation==2)
        {
            ypos++;
            if(ypos>=height)return false;
        }
        else if(orientation==3)
        {
            xpos--;
            if(xpos<0)return false;
        }
        
        if(grid[ypos][xpos]>0)return false;

        if(grid[ypos][xpos]!=-1)
        {
            for(int i=0;i<height;i++)
            {
                for(int j=0;j<width;j++)
                {
                    if(grid[i][j]==length)grid[i][j]=0;
                    else if(grid[i][j]>0)grid[i][j]++;
                }
            }
            grid[ypos][xpos]=1;
            for(int i=0;i<height;i++)
            {
                for(int j=0;j<width;j++)
                {
                    System.out.print(grid[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
        else
        {
            for(int i=0;i<height;i++)
            {
                for(int j=0;j<width;j++)
                {
                    if(grid[i][j]>0)grid[i][j]++;
                }
            }
        length++;
        grid[ypos][xpos]=1;

            while(grid[ypos][xpos]!=0)
            {
                ypos = rand.nextInt(height);
                xpos = rand.nextInt(width);
            }
        grid[ypos][xpos]=-1;

        }
        
        return true;
    }

    public int[][]getState()
    {
        return grid;
    }

    
}
