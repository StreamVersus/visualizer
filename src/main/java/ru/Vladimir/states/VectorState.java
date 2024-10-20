package ru.Vladimir.states;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.shape.Line;
import ru.Vladimir.Main;
import ru.Vladimir.Parser;
import ru.Vladimir.utils;

import java.util.ArrayList;
import java.util.List;

import static ru.Vladimir.Main.args;

public class VectorState extends BaseAppState {
    private Geometry grid;
    public static List<Line> vecArr;


    @Override
    protected void initialize(Application app) {
        if(args == null || args.length == 0) return;

        List<Line> vectors = new ArrayList<>();
        for (String arg : args) {
            var lineCoords = Parser.parseVector(arg);
            var line = new Line(new Vector3f(lineCoords[0], lineCoords[1], lineCoords[2]), new Vector3f(lineCoords[3], lineCoords[4], lineCoords[5]));
            vectors.add(line);
        }
        Mesh mesh = utils.linesToMesh(vectors, ColorRGBA.LightGray, 1f);
        grid = new Geometry("GridMesh", mesh);

        Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setBoolean("VertexColor", true);
        mat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);

        grid.setMaterial(mat);
        grid.setQueueBucket(RenderQueue.Bucket.Transparent);

        vecArr = vectors;
    }

    @Override
    protected void cleanup(Application application) {

    }

    @Override
    protected void onEnable() {
        ((Main)getApplication()).getRootNode().attachChild(grid);
    }

    @Override
    protected void onDisable() {
        grid.removeFromParent();
    }

    public static Float scalarProd(Line a, Line b){
        var ax = a.getEnd().x - a.getStart().x;
        var ay = a.getEnd().y - a.getStart().y;
        var az = a.getEnd().z - a.getStart().z;
        var bx = b.getEnd().x - b.getStart().x;
        var by = b.getEnd().y - b.getStart().y;
        var bz = b.getEnd().z - b.getStart().z;
        return ax * bx + ay * by + az * bz;
    }
    private static Float vectorLength(Line a){
        var ax = a.getEnd().x - a.getStart().x;
        var ay = a.getEnd().y - a.getStart().y;
        var az = a.getEnd().z - a.getStart().z;
        var sum = Math.pow(ax, 2) + Math.pow(ay, 2) + Math.pow(az, 2);
        return (float) Math.sqrt(sum);
    }
    public static float cosBetweenVectors(Line a, Line b){
        var scalar = scalarProd(a, b);
        var mult = vectorLength(a) * vectorLength(b);
        return scalar/mult;
    }
}
