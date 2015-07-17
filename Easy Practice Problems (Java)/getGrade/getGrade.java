int main() {
	double x = 0;	
	x = getGrade(60);
	System.out.println("Expected: 0.7 return: " + x);
	x = getGrade(63);
	System.out.println("Expected: 0.8 return: " + x);	
	x = getGrade(64);
	System.out.println("Expected: 0.9 return: " + x);	
	x = getGrade(65);	
	System.out.println("Expected: 1.0 return: " + x);	
	x = getGrade(92);	
	System.out.println("Expected: 3.7 return: " + x);	
	x = getGrade(93);	
	System.out.println("Expected: 3.8 return: " + x);	
	x = getGrade(94);	
	System.out.println("Expected: 3.9 return: " + x);	
	x = getGrade(95);	
	System.out.println("Expected: 4.0 return: " + x);
	
	x = getGrade(185);
	x = getGrade(-1);
	x = getGrade(101);
}

public static double getGrade(int score) {
    if(score < 0 || score > 100) {
        throw new IllegalArgumentException("score does not exist");
    }
    
    if(score < 60) {
        return 0;
    } 
    
    if(score >= 60 && score < 63) {
        return 0.7;
    }
    
    double grade = (score - 62) / 10.0 + 0.7;
    return grade;      
}
