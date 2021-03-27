public class Application {
	private Coach BackEnd;
	private StageC ConsoleUI;
	
	public Application() {
		this.BackEnd = new Coach();
		this.ConsoleUI = new StageC(BackEnd);
	}
	

	public static void main(String[] args) {
		Application app = new Application();
	}
}