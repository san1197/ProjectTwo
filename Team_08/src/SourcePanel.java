import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import Shapes.OvalShape;
import Shapes.RectangleShape;
import Shapes.Shape;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class SourcePanel extends Application {


    private Shape[] shapes = new Shape[500];  // Contains shapes the user has drawn.
    private int shapeCount = 0; // Number of shapes that the user has drawn.
    private Canvas canvas; // The drawing area where the user draws.
    private Color currentColor = Color.RED;  // Color to be used for new shapes.
    public Shape currentShape; //will be used when duplicating the image

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        canvas = makeCanvas();
        //Panel panel = new Panel();
        canvas = makeCanvas();
        paintCanvas();
        StackPane canvasHolder = new StackPane(canvas);
        canvasHolder.setStyle("-fx-border-width: 2px; -fx-border-color: #444");
        BorderPane root = new BorderPane(canvasHolder);
        root.setStyle("-fx-border-width: 1px; -fx-border-color: black");
        //root.setBottom(makeToolPanel(canvas));
        root.setLeft(makeToolPanel(canvas));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Project02 Team XX");
        stage.setResizable(false);
        stage.show();
    }

    private Canvas makeCanvas() {
        // Creates a canvas, and add mouse listeners to implement dragging.
        // The listeners are given by methods that are defined below.
        Canvas canvas = new Canvas(800,600);
        canvas.setOnMousePressed( this::mousePressed );
        canvas.setOnMouseReleased( this::mouseReleased );
        canvas.setOnMouseDragged( this::mouseDragged );

        canvas.setOnMouseClicked(this::mouseClicked);
        //canvas.setOnMouseEntered(this::mouseEntered);
        return canvas;
    }

    private VBox makeToolPanel(Canvas canvas) {
        // Make a pane containing the buttons that are used to add shapes
        // and the pop-up menu for selecting the current color.
        //OvalShape ovalShape = new OvalShape();
        FileInputStream input = null;
//        try {
//            input = new FileInputStream("src/image/circle.png");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        //Image image = new Image(input);
        //ImageView imageView = new ImageView(image);
        Button ovalButton = new Button("C");
        ovalButton.setOnAction( (e) -> addShape( new OvalShape(),10,100,50,50 ));
//        try {
//            input = new FileInputStream("src/image/rectangle.png");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        image = new Image(input);
//        imageView = new ImageView(image);

        Button rectButton = new Button("R");
        rectButton.setOnAction( (e) -> addShape( new RectangleShape(),10,400,75,50 ) );
        //Button roundRectButton = new Button("Add a RoundRect");
        //roundRectButton.setOnAction( (e) -> addShape( new RoundRectShape() ) );
        ComboBox<String> combobox = new ComboBox<>();
        combobox.setEditable(false);
        Color[] colors = { Color.RED, Color.GREEN, Color.BLUE, Color.CYAN,
                Color.MAGENTA, Color.YELLOW, Color.BLACK, Color.WHITE };
        String[] colorNames = { "Red", "Green", "Blue", "Cyan",
                "Magenta", "Yellow", "Black", "White" };
        combobox.getItems().addAll(colorNames);
        combobox.setValue("Red");
        combobox.setOnAction(
                e -> currentColor = colors[combobox.getSelectionModel().getSelectedIndex()] );
        VBox tools = new VBox(5);
        tools.getChildren().add(ovalButton);
        tools.getChildren().add(rectButton);
        //tools.getChildren().add(roundRectButton);
        tools.getChildren().add(combobox);
        tools.setStyle("-fx-border-width: 3px; -fx-border-color: transparent; -fx-background-color: white");
        return tools;
    }

    private void paintCanvas() {

        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.WHITE); // Fill with white background.
        g.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        for (int i = 0; i < shapeCount; i++) {
            Shape s = shapes[i];
            s.draw(g);
        }
    }

    private void addShape(Shape shape,int left,int top,int width,int height) {

        shape.setColor(currentColor);
        shape.reshape(left,top,width,height);
        shapes[shapeCount] = shape;
        shapeCount++;
        paintCanvas();
    }


    private Shape shapeBeingDragged = null;

    private int prevDragX;  // During dragging, these record the x and y coordinates of the
    private int prevDragY;  //    previous position of the mouse.

    private void mousePressed(MouseEvent evt) {

        int x = (int)evt.getX();  // x-coordinate of point where mouse was clicked
        int y = (int)evt.getY();  // y-coordinate of point
        for ( int i = shapeCount - 1; i >= 0; i-- ) {  // check shapes from front to back
            Shape s = shapes[i];
            currentShape = shapes[shapeCount - 1];
            if (s.containsPoint(x,y)) {
                shapeBeingDragged = s;
                prevDragX = x;
                prevDragY = y;
                if (evt.isShiftDown()) { // s should be moved on top of all the other shapes
                    for (int j = i; j < shapeCount-1; j++) {
                        // move the shapes following s down in the list
                        shapes[j] = shapes[j+1];
                    }
                    shapes[shapeCount-1] = s;  // put s at the end of the list
                    paintCanvas();  // repaint canvas to show s in front of other shapes
                }
                return;
            }
        }
    }

    private void mouseDragged(MouseEvent evt) {
        // User has moved the mouse.  Move the dragged shape by the same amount.
        int count = 0;

//        if(count >1)
//            return;
        int x = (int)evt.getX();
        int y = (int)evt.getY();
        if (shapeBeingDragged != null) {
            //count++;
            shapeBeingDragged.moveBy(x - prevDragX, y - prevDragY);
            prevDragX = x;
            prevDragY = y;
            paintCanvas();      // redraw canvas to show shape in new position

        }
    }

    private void mouseClicked(MouseEvent evt) {
        // User has clicked the mouse.
        if(currentShape.toString().contains("Oval"))
            addShape(new OvalShape(),(int)evt.getX(),(int)evt.getY(),50,50 );
        else if(currentShape.toString().contains("Rect"))
            addShape(new RectangleShape(),(int)evt.getX(),(int)evt.getY(),75,50 );

    }

    private void mouseReleased(MouseEvent evt) {
        // User has released the mouse.  Move the dragged shape, then set
        // shapeBeingDragged to null to indicate that dragging is over.
        shapeBeingDragged = null;
    }
}