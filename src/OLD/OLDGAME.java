package OLD;
import java.util.Random;

public class OLDGAME 
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

    public OLDGAME(int h,int w,int seed)
    {
        width = w;
        height = h;
        rand = new Random();
        grid = generateMap(h,w,2);
        orientation = 0;
        length = 3;
        grid[h/2][w/2] = 1;//distance from head
        grid[h/2][w/2+1] = 2;
        grid[h/2][w/2+2] = 3;
    }
    private int[][] generateMap(int height,int width,int layout)
    {
        int[][]grid = new int[height][width];
        if(layout==0)
        {
            for(int i=0;i<height;i++)
            {
                for(int j=0;j<width;j++)
                {
                    grid[i][j]=0;
                }
            }
        }
        if(layout==1)
        {
            for(int i=0;i<height;i++)
            {
                for(int j=0;j<width;j++)
                {
                    if(i==0||j==0||i==height-1||j==width-1)grid[i][j]=-2;
                    else grid[i][j]=0;
                }
            }
        }
        if(layout==2)
        {
            int gaph = width/4;
            int gapv = height/4;
            for(int i=0;i<height;i++)
            {
                for(int j=0;j<width;j++)
                {
                    if((i+gapv<height-1&&i-gapv>0)||(j+gaph<width-1&&j-gaph>0)) grid[i][j]=0;
                    else grid[i][j]=-2;
                }
            }
        }
        if(layout==3)
        {
            int gaph = width/4;
            int gapv = height/4;
            for(int i=0;i<height;i++)
            {
                for(int j=0;j<width;j++)
                {
                    if((i+gapv<height-1&&i-gapv>0)||(j+gaph<width-1&&j-gaph>0)||!((i==0||j==0||i==height-1||j==width-1))) grid[i][j]=0;
                    else grid[i][j]=-2;
                }
            }
        }
        int ypos = rand.nextInt(height);
        int xpos = rand.nextInt(width);
        while(grid[ypos][xpos]!=0)
        {
            ypos = rand.nextInt(height);
            xpos = rand.nextInt(width);
        }
        grid[ypos][xpos]=-1;
        return grid;
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
            if(ypos<0)ypos=height-1;
        }
        else if(orientation==1)
        {
            xpos++;
            if(xpos>=width)xpos=0;
        }
        else if(orientation==2)
        {
            ypos++;
            if(ypos>=height)ypos=0;
        }
        else if(orientation==3)
        {
            xpos--;
            if(xpos<0)xpos=width-1;
        }
        
        System.out.println("xpos: "+xpos+" ypos: "+ypos);

        if(grid[ypos][xpos]>0||grid[ypos][xpos]==-2)return false;

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
            /*for(int i=0;i<height;i++)
            {
                for(int j=0;j<width;j++)
                {
                    System.out.print(grid[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();*/
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
