package miklos.martin.learningandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Layout;
import android.view.View;

import java.util.Random;

/**
 * Animated view
 */
public class AnimatedView extends View {

    Bitmap greenBall;
    int changingY;
    Typeface font;
    int changingX;

    public AnimatedView ( Context context ) {
        super( context );

        greenBall = BitmapFactory.decodeResource( getResources(), R.drawable.greenball );
        changingY = 0;
        changingX = 50;
        font = Typeface.createFromAsset( context.getAssets(), "fonts-japanese-gothic.ttf" );
    }

    @Override
    protected void onDraw ( Canvas canvas ) {
        super.onDraw( canvas );

        canvas.drawColor( Color.WHITE );

        Paint text = new Paint();
        text.setARGB( 50, 128, 128, 128 );
        text.setTextAlign( Paint.Align.LEFT );
        text.setTextSize( 40 );
        text.setFakeBoldText( true );

        canvas.drawText( "Hey, firstname!", 20, 80, text );

        canvas.drawBitmap( greenBall, changingX, changingY, null );

        if ( changingY < canvas.getHeight() ) {
            changingY += 10;
        } else {
            changingY = 0;
        }

        Random random = new Random();
        changingX += random.nextInt( 10 ) * ( random.nextBoolean() ? 1 : -1 );

        Rect middleRect = new Rect();
        middleRect.set( 0, 150, canvas.getWidth(), 300 );

        Paint blue =  new Paint();
        blue.setColor( Color.BLUE );

        canvas.drawRect( middleRect, blue );

        invalidate();
    }
}
