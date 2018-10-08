package zl.pattern.builder;

public class Computer {

    private final String model;
    private final String memory;
    private int graphic;
    private String board;
    private String display;
    private String processor;

    private Computer(ComputerBuilder builder){
        this.model = builder.model;
        this.memory = builder.memory;
        this.graphic = builder.graphic;
        this.display = builder.display;
        this.board = builder.board;
        this.processor = builder.processor;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "model='" + model + '\'' +
                ", memory='" + memory + '\'' +
                ", graphic=" + graphic +
                ", board='" + board + '\'' +
                ", display='" + display + '\'' +
                ", processor='" + processor + '\'' +
                '}';
    }

    public void setGraphic(int graphic) {
        this.graphic = graphic;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getModel() {
        return model;
    }

    public String getMemory() {
        return memory;
    }

    public int getGraphic() {
        return graphic;
    }

    public String getBoard() {
        return board;
    }

    public String getDisplay() {
        return display;
    }

    public String getProcessor() {
        return processor;
    }

    public static class ComputerBuilder{
        private final String model;         //型号,
        private final String memory;        //内存
        private int graphic;             //显卡
        private String board;               //主机
        private String display;             //显示器
        private String processor;           //处理器
        public ComputerBuilder(String model,String memory){
            this.model = model;
            this.memory = memory;
        }
        public ComputerBuilder graphic(int graphic){
            this.graphic = graphic;
            return this;
        }
        public ComputerBuilder board(String board){
            this.board = board;
            return this;
        }
        public ComputerBuilder display(String display){
            this.display = display;
            return this;
        }
        public ComputerBuilder processor(String processor){
            this.processor = processor;
            return this;
        }
        public Computer build(){
            return new Computer(this);
        }
    }
}
