package ru.Vladimir;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer;
import com.jme3.scene.shape.Line;

import java.util.List;

public class utils {
    public static Mesh linesToMesh(List<Line> lines, ColorRGBA colorr, float alpha){
        float[] pos = new float[lines.size() * 2 * 3];
        float[] color = new float[lines.size() * 2 * 4];
        int posIndex = 0;
        int colorIndex = 0;

        for(Line l : lines) {
            if(l == null) continue;
            var endv = l.getEnd();
            var startv = l.getStart();
            pos[posIndex++] = endv.x;
            pos[posIndex++] = endv.y;
            pos[posIndex++] = endv.z;
            pos[posIndex++] = startv.x;
            pos[posIndex++] = startv.y;
            pos[posIndex++] = startv.z;

            color[colorIndex++] = colorr.a;
            color[colorIndex++] = colorr.b;
            color[colorIndex++] = colorr.g;
            color[colorIndex++] = alpha;
            color[colorIndex++] = colorr.a;
            color[colorIndex++] = colorr.b;
            color[colorIndex++] = colorr.g;
            color[colorIndex++] = alpha;
        }

        Mesh mesh = new Mesh();
        mesh.setMode(Mesh.Mode.Lines);
        mesh.setBuffer(VertexBuffer.Type.Position, 3, pos);
        mesh.setBuffer(VertexBuffer.Type.Color, 4, color);
        mesh.updateBound();
        return mesh;
    }
}
