public boolean isValid(String){
	Stack<Character> stack =  new Stack<>();

	for(char c:s.toCharArray()){
		if(c == '(')
			stack.push(')');
		if(c == '{')
			stack.push('}');
		if(c == '[')
			stack.push(']');
		if(stack.pop() !=c || stack.isEmpty())
			return false;
	}
	return stack.isEmpty();
}