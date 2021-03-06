package quiz03;

import java.util.List;

public class Exam {

	private List<Integer> score;  // 5개의 점수
	private double average;  // 평균
	private char grade;  // A ~ F
	
	public double getAve() {
		int total = 0;
		for(int i = 0, length = score.size(); i < length; i++) {
			total += score.get(i);
		}
		average = (double)total / score.size();
		return average;
	}
	public char grade() {
		grade = 'F';
		if (getAve() >= 90) {
			grade = 'A';
		}else if (getAve() >= 80) {
			grade = 'B';
		}else if (getAve() >= 70) {
			grade = 'C';
		}else if (getAve() >= 60) {
			grade = 'D';
		}
		return grade;
	}
	public void info() {
		System.out.println("score: " + score.toString());
		System.out.println("average: " + getAve());
		System.out.println("grade: " + grade());
	}
	
	public List<Integer> getScore() {
		return score;
	}
	public void setScore(List<Integer> score) {
		this.score = score;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {
		this.grade = grade;
	}
	
	
	
	
	
	
}
