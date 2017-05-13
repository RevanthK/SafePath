package com.example.revanthkorrapolu.yournotmydad;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.esri.android.map.MapView;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleMarkerSymbol;

public class MainActivity extends AppCompatActivity {

    private MapView mMapView;
    private final SpatialReference wgs84 = SpatialReference.create(4326);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMapView = (MapView) findViewById(R.id.map);

        /*
        //create a simple marker symbol
        SimpleMarkerSymbol symbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, Color.RED, 12); //size 12, style of circle

//add a new graphic with a new point geometry
        Point graphicPoint = new Point(-226773, 6550477, SpatialReferences.getWebMercator());
        Graphic graphic = new Graphic(graphicPoint, symbol);
        graphicsOverlay.getGraphics().add(graphic);

*/
        setContentView(R.layout.activity_main);
    }



}
