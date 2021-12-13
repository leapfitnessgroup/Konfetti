package nl.dionsegijn.simple_demo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti_core.NewEmitter.Emitter;
import nl.dionsegijn.konfetti_core.NewEmitter.EmitterConfig;
import nl.dionsegijn.konfetti_core.Party;
import nl.dionsegijn.konfetti_core.PartyFactory;
import nl.dionsegijn.konfetti_core.Position;
import nl.dionsegijn.konfetti_core.Velocity;
import nl.dionsegijn.konfetti_core.models.Shape;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_heart);
        final Shape.DrawableShape drawableShape = new Shape.DrawableShape(drawable, true);

        final KonfettiView konfettiView = findViewById(R.id.konfettiView);
        EmitterConfig emitterConfig = new Emitter(5L, TimeUnit.SECONDS).perSecond(50);
        PartyFactory partyFactory = new PartyFactory();
        Party party = partyFactory.createParty(90, 359, new Velocity(5f, null), new Position.relative(0.5, 0.5), emitterConfig);
        konfettiView.setOnClickListener(view ->
                konfettiView.start(party)
        );

//                konfettiView.build()
//                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
//                .setDirection(0.0, 359.0)
//                .setSpeed(1f, 5f)
//                .setFadeOutEnabled(true)
//                .setTimeToLive(2000L)
//                .addShapes(new Shape.Rectangle(0.2f), drawableShape)
//                .addSizes(new Size(12, 5f))
//                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
//                .streamFor(300, 5000L));
    }
}
