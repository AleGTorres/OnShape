package com.example.onshape.ui.routine;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onshape.R;
import com.example.onshape.data.Routine;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.Lifecycle;

import java.util.ArrayList;

public class RoutineListFragment extends Fragment implements RoutineAdapter.OnRoutineInteractionListener {

    private RoutineViewModel routineViewModel;
    private RecyclerView recyclerView;
    private RoutineAdapter adapter;
    private int userId;
    private LinearLayout emptyView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = requireActivity().getSharedPreferences("OnShape_prefs", Context.MODE_PRIVATE);
        userId = prefs.getInt("LOGGED_IN_USER_ID", -1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routine_list, container, false);

        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_logout) {
                    logout();
                    return true;
                }
                return false;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);

        recyclerView = view.findViewById(R.id.recycler_view_routines);
        emptyView = view.findViewById(R.id.empty_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new RoutineAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.fab_add_routine);
        fab.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_routineListFragment_to_createRoutineFragment);
        });

        routineViewModel = new ViewModelProvider(this).get(RoutineViewModel.class);

        if (userId != -1) {
            routineViewModel.getUserRoutines(userId).observe(getViewLifecycleOwner(), routines -> {
                if (routines == null || routines.isEmpty()) {
                    recyclerView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
                adapter.setRoutines(routines);
            });
        }

        return view;
    }

    private void logout() {
        SharedPreferences prefs = requireActivity().getSharedPreferences("OnShape_prefs", Context.MODE_PRIVATE);
        prefs.edit().remove("LOGGED_IN_USER_ID").apply();

        Navigation.findNavController(requireView()).navigate(R.id.loginFragment);
    }

    @Override
    public void onRoutineClick(Routine routine) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("routine", routine);
        Navigation.findNavController(requireView()).navigate(R.id.action_routineListFragment_to_routineDetailFragment, bundle);
    }

    @Override
    public void onStartRoutine(Routine routine) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("routine", routine);
        Navigation.findNavController(requireView()).navigate(R.id.action_routineListFragment_to_activeWorkoutFragment, bundle);
    }
}