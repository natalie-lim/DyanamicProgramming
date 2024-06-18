
public class DynamicProgramming {
	
    // Every day for the rest of the year, you're going to be given a choice between two jobs to do: 
    // one that is LOW stress, and one that is HIGH stress.  Each job pays out a dollar amount; 
    // *usually* the high stress jobs pay more.  However, after doing a high stress job, you need to 
    // REST for a day.

    // Given a list of all the payouts for all the low stress and high stress jobs, 
    // what is the most amount of money you can get?
    
    // You can assume lowPayouts.length == highPayouts.length
    public static int hiLoStress(int[] lowPayouts, int[] highPayouts) {
    	//money[i] is the money you have on that day
    	int[] money = new int[lowPayouts.length + 1];
    	money[0] = 0;
    	money[1] = lowPayouts[0];
    	for (int i = 2; i < money.length; i++) {
    		if ((highPayouts[i-2] + money[i-2]) > (money[i-1] + lowPayouts[i-1])) {
    			money[i] = highPayouts[i-2] + money[i-2];
    		} else {
    			money[i] = lowPayouts[i-1] + money[i-1];
    		}
    	}
    	return money[lowPayouts.length];
    }
    
    
	// You are partaking in a scavenger hunt!
    // You've gotten a secret map to find many of the more difficult
    // items, but they are only available at VERY specific times at
    // specific places.  You have an array, times[], that lists at which
    // MINUTE an item is available, in increasing order.
    // Items in the ScavHunt are worth varying numbers of points.
    // You also have an array, points[], same length as times[],
    // that lists how many points each of the corresponding items is worth.
    // Problem is: to get from one location to the other takes 5 minutes,
    // so if there is an item, for example, available at time 23 and another
    // at time 27, it's just not possible for you to make it to both: you'll
    // have to choose!
    // Write a method that returns the maximum POINTS you can get.
    /**
     * public static int scavHunt(int[] times, int[] points) {
		return helpScavHunt (times, points, 0);
	}
	public static int helpScavHunt(int[] times, int[] points, int startIndex) {
		if (startIndex < 0 || startIndex > times.length - 1) {
			return 0;
		}
		//decide to go to first option
		int firstOption = points[startIndex] + helpScavHunt(times, points, findNextIdx(times, startIndex));
		//which one is better? going to the first option or not
		return Math.max(firstOption, helpScavHunt(times, points, startIndex+1));
	}
	public static int findNextIdx(int[] times, int startIndex) {
		for (int i = startIndex + 1; i < times.length; i++) {
			if (times[i] - times[startIndex] >= 5) {
				return i;
			}
		}
		return -1;
	}
   */
    
    public static int scavHunt(int[] times, int[] points) {
    	int[] tally = new int[times.length + 1];
    	tally[times.length] = 0;
    	tally[times.length-1] = points[times.length-1];
    	for (int idx = times.length - 2; idx >= 0; idx--) {
    		int max = 0; 
    		for (int i = findNextIdx(times, idx); i <= points.length; i++) {
    			if (tally[i] > max) {
    				max = tally[i];
    			}
    		}
    		tally[idx] = max + points[idx]; 	
    	}
    	return tally[0];
	}
    
    public static int findNextIdx(int[] times, int startIndex) {
    	for (int i = startIndex + 1; i < times.length; i++) {
			if (times[i] - times[startIndex] >= 5) {
				return i;
			}
		}
		return times.length;
    }
    


	/* Uses memoization to calculate the route which grants the most cookies, 
	 * starting at [0][0], only going right or down at each point
	 *  public CookieMonster(int [][] cookieGrid) {
        this.cookieGrid = cookieGrid;
        this.numRows    = cookieGrid.length;
        this.numCols    = cookieGrid[0].length;
    }

	/* RECURSIVELY calculates the route which grants the most cookies.
	 * Returns the maximum number of cookies attnainable.
	public int recursiveCookies() {
		return helpRecursive (0, 0, 0);
	}
	public int helpRecursive(int numCookies, int row, int column) {
		if (!canGo(row, column)) {
			return numCookies;
		}
		numCookies += cookieGrid[row][column];
		return Math.max(helpRecursive(numCookies, row + 1, column), helpRecursive(numCookies, row, column+1));
	}
 */
    
	public static int dynamicCookies(int[][] cookieGrid) {
		int[][] points = new int[cookieGrid.length+1][cookieGrid[0].length+1];
		
		for (int r = cookieGrid.length - 1; r >= 0; r--) {
			for (int c = cookieGrid[0].length - 1; c >= 0; c--) {
				if (cookieGrid[r][c] == -1){
					points[r][c] = 0;
				} else {
					points[r][c] = Math.max(points[r+1][c], points[r][c+1]) + cookieGrid[r][c];
				}
			}
		}
		return points[0][0];
	}
    
    


}
