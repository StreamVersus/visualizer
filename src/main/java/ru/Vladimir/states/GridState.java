package ru.Vladimir.states;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.*;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.*;
import com.jme3.scene.shape.Line;
import ru.Vladimir.Main;
import ru.Vladimir.utils;

import java.util.ArrayList;
import java.util.List;

public class GridState extends BaseAppState {
 
    private final float cellSize;
    private final float cellAmount;

    private Geometry grid;
    
    public GridState(float cellSize, float cellAmount) {
        this.cellSize = cellSize;
        this.cellAmount = cellAmount;
    }
    
    @Override
    protected void initialize(Application app) {
        //List of computed vectors for render
        List<Line> lines = new ArrayList<>();

        float lenght = cellAmount * cellSize;
        float constant = -(lenght / 2);
        float moved = -(lenght / 2);

        for (int i = 0; i <= cellAmount; i++){
            lines.add(new Line(new Vector3f(constant, 0, -moved), new Vector3f(-constant, 0, -moved)));
            lines.add(new Line(new Vector3f(moved, 0, -constant), new Vector3f(moved, 0, constant)));
            moved += cellSize;
        }

        Mesh mesh = utils.linesToMesh(lines, ColorRGBA.White, 0.3f);
        grid = new Geometry("GridMesh", mesh);
        Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setBoolean("VertexColor", true);
        mat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        grid.setMaterial(mat);
        grid.setQueueBucket(RenderQueue.Bucket.Transparent);
    }
    
    @Override
    protected void cleanup(Application app) {
    }
    
    @Override
    protected void onEnable() {
        ((Main)getApplication()).getRootNode().attachChild(grid);
    }
    
    @Override
    protected void onDisable() {
        grid.removeFromParent();
    }
}
