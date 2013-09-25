package miklos.martin.learningandroid;

import android.os.Bundle;
import android.view.MotionEvent;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import java.util.List;

/**
 * Playing with google maps
 */
public class Maps extends MapActivity {

    MapView  map;
    long start, stop;

    @Override
    protected void onCreate ( Bundle bundle ) {
        super.onCreate( bundle );

        initialize();
    }

    private void initialize () {
        map = (MapView) findViewById( R.id.map );
        map.setBuiltInZoomControls( true );

        List<Overlay> overlayList = map.getOverlays();
        overlayList.add( new TouchOverlay() );
    }

    @Override
    protected boolean isRouteDisplayed () {
        return false;
    }

    public class TouchOverlay extends Overlay {

        @Override
        public boolean onTouchEvent ( MotionEvent event, MapView mapView ) {

            switch ( event.getAction() ) {
                case MotionEvent.ACTION_DOWN:
                    start = event.getEventTime();
                    break;
                case MotionEvent.ACTION_UP:
                    stop = event.getEventTime();
                    break;
            }


            return false;
        }
    }
}
