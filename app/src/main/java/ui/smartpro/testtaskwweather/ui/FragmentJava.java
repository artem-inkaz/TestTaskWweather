package ui.smartpro.testtaskwweather.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import ui.smartpro.testtaskwweather.R;
import ui.smartpro.testtaskwweather.api.State;
import ui.smartpro.testtaskwweather.model.WeatherModel;

public class FragmentJava extends Fragment {

    private WeatherViewModel weatherViewModel;

    private Long zipCodeInput;

    private String nameLocation;

    private State stateProgress;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        weatherViewModel =
                new ViewModelProvider(this, new WeatherViewModelFactory()).get(WeatherViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_weather,container,  false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button getweatherBtn = view.findViewById(R.id.getweatherBtn);
        EditText zipCode = view.findViewById(R.id.zipCodeET);
        TextView locationField = view.findViewById(R.id.locationField);
        TextView temperatureField = view.findViewById(R.id.temperatureField);
        TextView windField = view.findViewById(R.id.windField);
        TextView humidityField = view.findViewById(R.id.humidityField);
        TextView visibilityField = view.findViewById(R.id.visibilityField);
        TextView sunriseField = view.findViewById(R.id.sunriseField);
        TextView sunsetField = view.findViewById(R.id.sunsetField);

        zipCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                zipCodeInput= Long.valueOf(String.valueOf(s)).longValue();
                weatherViewModel.validateInput(s.toString());
            }
        });

        getweatherBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                weatherViewModel.updateData(zipCodeInput);

                weatherViewModel.getWeatherLive()
                        .observe(getViewLifecycleOwner(), weatherModels -> {
                           nameLocation = weatherModels.getName();
                            locationField.setText(weatherModels.getName());
                            temperatureField.setText(weatherModels.getTemperature()) ;
                            windField.setText(weatherModels.getWind()) ;
                            humidityField.setText(weatherModels.getHumidity()) ;
                            visibilityField.setText(weatherModels.getVisibility()) ;
                            sunriseField.setText(weatherModels.getSunrise()) ;
                             sunsetField.setText(weatherModels.getSunset()) ;
                        });
            }
        });

        weatherViewModel.saveEnabled().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
//                buttonUpdate.setEnabled(aBoolean);
                zipCode.setEnabled(true);
            }
        });
    }
}
