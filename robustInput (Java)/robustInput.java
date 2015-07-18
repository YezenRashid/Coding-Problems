int main() {	
	Scanner console = new Scanner(System.in);
	System.out.print("Type your age: ");

	while(!console.hasNextInt()) {
   		String trash = console.next();
   		System.out.print("Type your age: ");
	}

	int age = console.nextInt();

	System.out.print("Type your GPA: ");
	while(!console.hasNextDouble()) {
  		String trash = console.next();
   		System.out.print("Type your GPA: ");
	}

	double gpa = console.nextDouble();
	System.out.println("age = " + age + ", GPA = " + gpa);
}
