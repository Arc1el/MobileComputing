package com.example.myapp12.ui.slideshow;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapp12.R;
import com.example.myapp12.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
        dlg.setTitle("좋아하는 버전?");
        dlg.setIcon(R.mipmap.ic_launcher);

        final String[] versionArray = new String[]{"마시멜로", "누가", "오레오"};
        dlg.setSingleChoiceItems(versionArray, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                textView.setText(versionArray[i]);
            }
        });

        View dialogview;
        dialogview = View.inflate(getActivity(), R.layout.dialog_view, null);
        dlg.setView(dialogview);

        dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText name = dialogview.findViewById(R.id.edit_text);
                textView.setText(name.getText().toString());
            }
        });
        dlg.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity().getApplicationContext(), "CANCELED", Toast.LENGTH_SHORT).show();
            }
        });

        dlg.show();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}