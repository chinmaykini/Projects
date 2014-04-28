package com.ck.tree.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.ck.tree.model.TreeNode;

public class TreeFunctionsImpl implements TreeFunctions{

	public static final Integer MAX_SIZE = 100;
	
	
	@Override
	public String BFS(TreeNode root) {
		// TODO Auto-generated method stub
		
		StringBuilder result = new StringBuilder();
		
		if( root == null ) return "";
		
//		Queue<TreeNode> minHeap = new PriorityQueue<TreeNode>(MAX_SIZE);
//		Queue<TreeNode> maxHeap =new PriorityQueue<TreeNode>(MAX_SIZE,Collections.reverseOrder());
//		Stack<TreeNode> myStack = new Stack<TreeNode>();
		
		Queue<TreeNode> myQueue = new LinkedList<TreeNode>();


		myQueue.add(root);
		while(!myQueue.isEmpty())
		{
			TreeNode currentNode = myQueue.remove();
			if(currentNode!=null && currentNode.data!=null)
			{
				result.append(currentNode.data.toString()).append(",");
				if(currentNode.left != null) myQueue.add(currentNode.left );
				if(currentNode.right != null) myQueue.add(currentNode.right);
			}
		}
		
		return(result.toString());
	}




	@Override
	public String DFS(TreeNode root) {
		
		StringBuilder result = new StringBuilder();
		
		if( root == null ) return "";
		
//		Queue<TreeNode> minHeap = new PriorityQueue<TreeNode>(MAX_SIZE);
//		Queue<TreeNode> maxHeap =new PriorityQueue<TreeNode>(MAX_SIZE,Collections.reverseOrder());
//		Queue<TreeNode> myQueue = new LinkedList<TreeNode>();
		
		Stack<TreeNode> myStack = new Stack<TreeNode>();

		myStack.push(root);

		while(!myStack.isEmpty())
		{
			TreeNode currentNode = myStack.pop();
			if(currentNode!=null)
			{
				result.append(currentNode.data.toString()).append(",");
				if(currentNode.right != null) myStack.push(currentNode.right);
				if(currentNode.left != null) myStack.push(currentNode.left );

			}
		}
		
		return(result.toString());
	}
	
	@Override
	public String inorder(TreeNode node){
		
		StringBuilder result = new StringBuilder();
		if( node == null) return "";
		
		result.append(inorder(node.left));
		result.append(",").append(node.data.toString());
		result.append(inorder(node.right));
		
		return result.toString();
	}




	@Override
	public ArrayList<ArrayList<Integer>> levelOrderTraversal(TreeNode node) {
		
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		ArrayList<Integer> level = new ArrayList<Integer>();
		
		if(node == null ) return result;
		
		int currentLevel = 1;
		int nextLevel = 0;
		queue.add(node);
		
		while(!queue.isEmpty()){
			TreeNode current = queue.poll();
			level.add(current.data);
			currentLevel --;
			if(current!=null){
				
				if(current.left!=null){
					queue.add(current.left);
					nextLevel++;
				}
				if(current.right!=null){
					queue.add(current.right);
					nextLevel++;
				}
				
				if(currentLevel==0){
					currentLevel = nextLevel;
					nextLevel = 0;
					result.add(level);
					level = new ArrayList<Integer>();
				}
			}
		}
		return result;
	}




	@Override
	public TreeNode rotateUpsideDown(TreeNode root) {
		
		return rotateUpsideDown(root,null);
		
	}

	private TreeNode rotateUpsideDown(TreeNode currentRoot, TreeNode newRightChild) {

		if(currentRoot == null) return null;
//		if(newRightChild == null ) return currentRoot;

		TreeNode leftChild = currentRoot.left;
		TreeNode rightChild = currentRoot.right;
		
		TreeNode nextLevelRoot = null;
		if(leftChild!=null)
			nextLevelRoot = leftChild.left;
		
		if(rightChild!=null){
			leftChild.left = rightChild;
			leftChild.right = currentRoot;
			currentRoot.left = null;
			currentRoot.right = newRightChild;
			if(nextLevelRoot!=null)
				return rotateUpsideDown(nextLevelRoot,leftChild );
			
		} else {
			leftChild.right = currentRoot;
			currentRoot.left = null;
			currentRoot.right = newRightChild;
			if(nextLevelRoot!=null)
				return rotateUpsideDown(nextLevelRoot,leftChild );
			
		}
		
		return leftChild;

	}




	@Override
	public TreeNode trimBST(TreeNode root, int min, int max) {
	
		if( root == null ) return root;
		
		// POST ORDER Traversal
		// build the tree bottom up
		// if at a node its in not in the range return the left or right subtree accordingly
		root.left = trimBST(root.left, min, max);
		root.right = trimBST(root.right, min, max);
		
		if(root.data >= min && root.data <= max){
			return root;
		} else if(root.data < min){
			return root.right;
		} else if(root.data> max){
			return root.left;
		}
		return null;

	}




	@Override
	public TreeNode BstToSingleLinkedList(TreeNode root) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public TreeNode BstToDoubleLinkedList(TreeNode root) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public TreeNode BstToDoubleCircularLinkedList(TreeNode root) {
		// TODO Auto-generated method stub
		return treetoCLL(root);

	}

	private TreeNode treetoCLL(TreeNode root) {
		
		if(root==null) return  null;
		
		// blindly recurse left and right creating 2 lists
		TreeNode aList = treetoCLL(root.left);
		TreeNode bList = treetoCLL(root.right);
		
		// create a single node which is a circular one
		root.left = root;
		root.right = root;
		
		// join 3 circular lists int he order aList,root,bList
		aList = append(aList,root);
		aList = append(aList,bList);
		
		// return the start
		return aList;
	}


	private TreeNode append(TreeNode a, TreeNode b) {
		
		// edge case do not forget
		if(a == null) return b;
		if(b == null) return a;
		
		// need to join 2 circular lists
		// take the last elements of both the lists 
		// join the last of one with begin of another
		TreeNode aLast = a.left;
		TreeNode bLast = b.left;
		join(aLast,b);
		join(bLast,a);
		
		// return the start
		return a;
	}




	private void join(TreeNode a, TreeNode b) {
		//just do the bidirectional linking
		a.right = b;
		b.left = a;
		
	}
	
	public static void printInRange(Node root, int min, int max){
		if(root == null) return;
		
		if(root.data >= min && root.data <= max){
			System.out.println(root.data);
			printInRange(root.left, min, max);
			printInRange(root.right, min, max);
		} else if(root.data<min){
			printInRange(root.right, min, max);
		} else if(root.data>max){
			printInRange(root.left, min, max);
		}
	}
	
	public static void printSpiral(Node root){
		if(root == null) return;
		
		Stack<Node> stack1 = new Stack<Node>();
		Stack<Node> stack2 = new Stack<Node>();
		
		stack1.push(root);
		while(!stack1.isEmpty() || !stack2.isEmpty()){
			
			while(!stack1.isEmpty()){
				Node curr = stack1.pop();
				System.out.print(curr.data);
				System.out.print(",");
				if(curr.left != null)
					stack2.push(curr.left);
				if(curr.right != null)
					stack2.push(curr.right);
			}
			System.out.println("");
			while(!stack2.isEmpty()){
				Node curr = stack2.pop();
				System.out.print(curr.data);
				System.out.print(",");
				if(curr.right != null)
					stack1.push(curr.right);
				if(curr.left != null)
					stack1.push(curr.left);
			}
			System.out.println("");
		}
	}
	
		public static int sizeTree(Node root){
		if(root==null) return 0;
		return (sizeTree(root.left)+sizeTree(root.right)+1);
	}
	
	public static int heightTree(Node root){
		if(root==null) return 0;
		return (Math.max(heightTree(root.left), heightTree(root.right))+1);
	}

	@Override
	public void traverseSpiral(TreeNode root){
		int height = getTreeHeight(root);
		boolean alt = false;
		for(int i = 1; i <= height; i++){
			printSpiralLevel(root,i,alt);
			alt = !alt;
		}
		
	}




	private void printSpiralLevel(TreeNode root, int level, boolean alt) {
		if(root == null)
	        return;
	    if(level == 1)
	        System.out.print(root.data);
	    else if (level > 1)
	    {
	        if(alt)
	        {
	            printSpiralLevel(root.left, level-1, alt);
	            printSpiralLevel(root.right, level-1, alt);
	        }
	        else
	        {
	            printSpiralLevel(root.right, level-1, alt);
	            printSpiralLevel(root.left, level-1, alt);
	        }
	    }		
	}



	@Override
	public int getTreeHeight(TreeNode root) {
		if(root == null ) return 0;
		return(1+Math.max(getTreeHeight(root.left), getTreeHeight(root.right)));
	}

	@Override
	public TreeNode deleteLeaves(TreeNode root){
		
		if(root == null) return null;
		
		if(root.left==null && root.right == null){
			root = null;
			return null;	//** returning null after assigning root as null
		}else{
			
			// assigning return values of recursive calls to left and right
			root.left = deleteLeaves(root.left);
			root.right = deleteLeaves(root.right);
		}
		return root;	// return root;
	}

}
