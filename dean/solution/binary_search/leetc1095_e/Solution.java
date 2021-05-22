package binary_search.leetc1095_e;

import datastructure.MountainArray;

class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int maxK = findMaxK(mountainArr);
        int ascTargetIndex = findAscTargetIndex(target,mountainArr,maxK);
        
        if(ascTargetIndex==-1){
            return findDecTargetIndex(target,mountainArr,maxK);
        }
        return ascTargetIndex;
    }
    
    private int findMaxK(MountainArray mountainArr){
        int left =0;
        int right =mountainArr.length();
        
        while(left+1<right){
            int mid = (left+right)/2;
            if(mountainArr.get(mid-1)<mountainArr.get(mid)){
                left = mid;
            } else{
                right=mid;
            }
        }
        
        return mountainArr.get(left)>mountainArr.get(right)?left:right;
    }
    
    private int findAscTargetIndex(int target,MountainArray arr, int maxK){
        int left=0;
        int right=maxK;
        
        while(left+1<right){
            int mid = (left+right)/2;
            
            if(arr.get(mid)<target){
                left=mid;
            } else{
                right=mid;
            }
        }
        
        if(arr.get(left)==target){
            return left;
        }
        if(arr.get(right)==target){
            return right;
        }
        return -1;
    }
    
    private int findDecTargetIndex(int target,MountainArray arr, int maxK){
        int left=maxK;
        int right=arr.length()-1;
        
        while(left+1<right){
            int mid = (left+right)/2;
            
            if(arr.get(mid)>target){
                left=mid;
            } else{
                right=mid;
            }
        }
        
        if(arr.get(left)==target){
            return left;
        }
        if(arr.get(right)==target){
            return right;
        }
        return -1;
    }
}
