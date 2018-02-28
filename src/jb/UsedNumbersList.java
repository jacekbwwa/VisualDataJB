/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
package jb;

import java.util.ArrayList;
/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
public class UsedNumbersList {
    
    private ArrayList nums;

    public  UsedNumbersList(){
        nums = new ArrayList();
    }

    public void setNums(ArrayList nums){
        this.nums = nums;
    }

    public ArrayList getNums(){
        return nums;
    }
	
    public void insertNumber(int num){     	
    	nums.add(num);    	
    }
    
    public boolean checkIfUsedNumber(int n){
		
        for (int i=0; i< nums.size(); i++){
            if(n == (int)nums.get(i))
            {
                return true;
            }
        }
 	return false;  
      }
}

/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */