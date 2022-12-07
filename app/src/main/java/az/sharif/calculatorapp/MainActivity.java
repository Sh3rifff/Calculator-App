package az.sharif.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import az.sharif.calculatorapp.databinding.ActivityMainBinding;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    TextView resultTV, solutionTV;
    Button but0, but1, but2, but3, but4, but5, but6, but7, but8, but9;
    Button ac, pow, percent, divide, multiply, subst, sum, equal, common, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        resultTV = findViewById(R.id.resultTV);
        solutionTV = findViewById(R.id.solutionTV);

        but0 = findViewById(R.id.zero);
        but1 = findViewById(R.id.one);
        but2 = findViewById(R.id.two);
        but3 = findViewById(R.id.three);
        but4 = findViewById(R.id.four);
        but5 = findViewById(R.id.five);
        but6 = findViewById(R.id.six);
        but7 = findViewById(R.id.seven);
        but8 = findViewById(R.id.eight);
        but9 = findViewById(R.id.nine);
        ac = findViewById(R.id.ac);
        pow = findViewById(R.id.pow);
        percent = findViewById(R.id.percent);
        divide = findViewById(R.id.divide);
        multiply = findViewById(R.id.multiply);
        subst = findViewById(R.id.substriction);
        sum = findViewById(R.id.sum);
        equal = findViewById(R.id.equal);
        common = findViewById(R.id.common);
        clear = findViewById(R.id.clear);

        assignId(but0, R.id.zero);
        assignId(but1, R.id.one);
        assignId(but2, R.id.two);
        assignId(but3, R.id.three);
        assignId(but4, R.id.four);
        assignId(but5, R.id.five);
        assignId(but6, R.id.six);
        assignId(but7, R.id.seven);
        assignId(but8, R.id.eight);
        assignId(but9, R.id.nine);
        assignId(ac, R.id.ac);
        assignId(pow, R.id.pow);
        assignId(percent, R.id.percent);
        assignId(divide, R.id.divide);
        assignId(multiply, R.id.multiply);
        assignId(subst, R.id.substriction);
        assignId(sum, R.id.sum);
        assignId(equal, R.id.equal);
        assignId(common, R.id.common);
        assignId(clear, R.id.clear);
    }

    void assignId(Button btn, int id) {
        btn.findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTV.getText().toString();

        if (buttonText.equals("AC")) {
            solutionTV.setText("");
            resultTV.setText("");
            return;
        }
        if (buttonText.equals("=")) {
            solutionTV.setText(resultTV.getText());
            return;
        }
        if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }
        solutionTV.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Error")) {
            resultTV.setText(finalResult);
        }

    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Error";
        }
    }
}