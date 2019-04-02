package com.example.misha.myapplication;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.misha.myapplication.adapter.RecyclerViewAdapterEditSchedule;

import java.util.ArrayList;
import java.util.List;

public class FragmentMonday extends android.support.v4.app.Fragment {

    private View fragmentView;

    private RecyclerView rvLessons;


    List<Lesson> Monday;
    ArrayList<String> typelesson = new ArrayList<>();
    final ArrayList<String> subject_list = new ArrayList<>();
    final ArrayList<String> audience_list = new ArrayList<>();
    final ArrayList<String> educator_list = new ArrayList<>();
    ArrayList<String> calls_schedule = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.card_item_edit_monday_for_recycler, container, false);
        rvLessons = fragmentView.findViewById(R.id.rv_lessons_edit);


        RadioButton rb_lecture = rvLessons.findViewById(R.id.rb_lecture_monday);
        RadioButton rb_labwork = rvLessons.findViewById(R.id.rb_labwork_monday);
        RadioButton rb_practice = rvLessons.findViewById(R.id.rb_practice_monday);

        TextView NumberOne = rvLessons.findViewById(R.id.number_monday);

        TextView TimeOne = rvLessons.findViewById(R.id.time_monday);

        Spinner SubjectEditOne = rvLessons.findViewById(R.id.subject_edit_monday);

        Spinner AudienceEditOne = rvLessons.findViewById(R.id.audience_edit_monday);

        Spinner EducatorEditOne = rvLessons.findViewById(R.id.educator_edit_monday);

        Spinner typeEditOne_monday = rvLessons.findViewById(R.id.typeEdit_monday);

        RecyclerViewAdapterEditSchedule adapter = new RecyclerViewAdapterEditSchedule(Monday);
        rvLessons.setAdapter(adapter);

       /* NumberOne.setText(Monday.get(0).getId());
        TimeOne.setText(Monday.get(0).getTime());
        rb_lecture.setText(typelesson.get(0));
        rb_labwork.setText(typelesson.get(1));
        rb_practice.setText(typelesson.get(2));*/
       /* ArrayAdapter<String> subSpinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, subject_list);
        subSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> audSpinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, audience_list);
        audSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> eduSpinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, educator_list);
        eduSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
        /*SubjectEditOne.setAdapter(subSpinnerArrayAdapter);
        AudienceEditOne.setAdapter(audSpinnerArrayAdapter);
        EducatorEditOne.setAdapter(eduSpinnerArrayAdapter);
*/
        /*TextView copy_downOne = fragmentView.findViewById(R.id.copy_downOne_monday);
        TextView copy_downTwo = fragmentView.findViewById(R.id.copy_downTwo_monday);
        TextView copy_downThree = fragmentView.findViewById(R.id.copy_downThree_monday);
        TextView copy_downFour = fragmentView.findViewById(R.id.copy_downFour_monday);
        TextView copy_downFive = fragmentView.findViewById(R.id.copy_downFive_monday);
        TextView copy_upTwo = fragmentView.findViewById(R.id.copy_upTwo_monday);
        TextView copy_upThree = fragmentView.findViewById(R.id.copy_upThree_monday);
        TextView copy_upFour = fragmentView.findViewById(R.id.copy_upFour_monday);
        TextView copy_upFive = fragmentView.findViewById(R.id.copy_upFive_monday);
        TextView copy_upSix = fragmentView.findViewById(R.id.copy_upSix_monday);
        TextView clearOne = fragmentView.findViewById(R.id.clear_cardOne_monday);
        TextView clearTwo = fragmentView.findViewById(R.id.clear_cardTwo_monday);
        TextView clearThree = fragmentView.findViewById(R.id.clear_cardThree_monday);
        TextView clearFour = fragmentView.findViewById(R.id.clear_cardFour_monday);
        TextView clearFive = fragmentView.findViewById(R.id.clear_cardFive_monday);
        TextView clearSix = fragmentView.findViewById(R.id.clear_cardSix_monday);*/


        /*typeEditOne_monday.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonOne = typeEditOne_monday.indexOfChild(findViewById(typeEditOne_monday.getCheckedRadioButtonId()));
                int IdRadioButton = typeEditOne_monday.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(IdRadioButton);
                if (radioButton != null) {
                    MondayTypeLessonOne = radioButton.getText().toString();
                } else {
                    MondayTypeLessonOne = "";
                }
            }
        });

        typeEditTwo_monday.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonTwo = typeEditTwo_monday.indexOfChild(findViewById(typeEditTwo_monday.getCheckedRadioButtonId()));
                int IdRadioButtonTwo = typeEditTwo_monday.getCheckedRadioButtonId();
                RadioButton radioButtonTwo = findViewById(IdRadioButtonTwo);
                if (radioButtonTwo != null) {
                    MondayTypeLessonTwo = radioButtonTwo.getText().toString();
                } else {
                    MondayTypeLessonTwo = "";
                }
            }
        });

        typeEditThree_monday.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonThree = typeEditThree_monday.indexOfChild(findViewById(typeEditThree_monday.getCheckedRadioButtonId()));
                int IdRadioButtonThree = typeEditThree_monday.getCheckedRadioButtonId();
                RadioButton radioButtonThree = findViewById(IdRadioButtonThree);
                if (radioButtonThree != null) {
                    MondayTypeLessonThree = radioButtonThree.getText().toString();
                } else {
                    MondayTypeLessonThree = "";
                }
            }
        });

        typeEditFour_monday.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonFour = typeEditFour_monday.indexOfChild(findViewById(typeEditFour_monday.getCheckedRadioButtonId()));
                int IdRadioButtonFour = typeEditFour_monday.getCheckedRadioButtonId();
                RadioButton radioButtonFour = findViewById(IdRadioButtonFour);
                if (radioButtonFour != null) {
                    MondayTypeLessonFour = radioButtonFour.getText().toString();
                } else {
                    MondayTypeLessonFour = "";
                }
            }
        });

        typeEditFive_monday.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonFive = typeEditFive_monday.indexOfChild(findViewById(typeEditFive_monday.getCheckedRadioButtonId()));
                int IdRadioButtonFive = typeEditFive_monday.getCheckedRadioButtonId();
                RadioButton radioButtonFive = findViewById(IdRadioButtonFive);
                if (radioButtonFive != null) {
                    MondayTypeLessonFive = radioButtonFive.getText().toString();
                } else {
                    MondayTypeLessonFive = "";
                }
            }
        });
        typeEditSix_monday.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                IdRadioButtonSix = typeEditSix_monday.indexOfChild(findViewById(typeEditSix_monday.getCheckedRadioButtonId()));
                int IdRadioButtonSix = typeEditSix_monday.getCheckedRadioButtonId();
                RadioButton radioButtonSix = findViewById(IdRadioButtonSix);
                if (radioButtonSix != null) {
                    MondayTypeLessonSix = radioButtonSix.getText().toString();
                } else {
                    MondayTypeLessonSix = "";
                }
            }
        });


        copy_downOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SubjectEditTwo.setSelection(subject_list.indexOf(Monday.get(0).getSubjectEdit()));
                    AudienceEditTwo.setSelection(audience_list.indexOf(Monday.get(0).getAudienceEdit()));
                    EducatorEditTwo.setSelection(educator_list.indexOf(Monday.get(0).getEducator()));
                    switch (IdRadioButtonOne) {
                        case 0:
                            rb_lectureTwo.setChecked(true);
                            break;
                        case 1:
                            rb_labworkTwo.setChecked(true);
                            break;
                        case 2:
                            rb_practiceTwo.setChecked(true);
                            break;
                    }
                } catch (NullPointerException e) {
                }
            }
        });

        copy_downTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SubjectEditThree.setSelection(subject_list.indexOf(Monday.get(1).getSubjectEdit()));
                    AudienceEditThree.setSelection(audience_list.indexOf(Monday.get(1).getAudienceEdit()));
                    EducatorEditThree.setSelection(educator_list.indexOf(Monday.get(1).getEducator()));
                    switch (IdRadioButtonTwo) {
                        case 0:
                            rb_lectureThree.setChecked(true);
                            break;
                        case 1:
                            rb_labworkThree.setChecked(true);
                            break;
                        case 2:
                            rb_practiceThree.setChecked(true);
                            break;
                    }
                } catch (NullPointerException e) {
                }
            }
        });

        copy_downThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SubjectEditFour.setSelection(subject_list.indexOf(Monday.get(2).getSubjectEdit()));
                    AudienceEditFour.setSelection(audience_list.indexOf(Monday.get(2).getAudienceEdit()));
                    EducatorEditFour.setSelection(educator_list.indexOf(Monday.get(2).getEducator()));
                    switch (IdRadioButtonThree) {
                        case 0:
                            rb_lectureFour.setChecked(true);
                            break;
                        case 1:
                            rb_labworkFour.setChecked(true);
                            break;
                        case 2:
                            rb_practiceFour.setChecked(true);
                            break;
                    }
                } catch (NullPointerException e) {
                }
            }
        });
        copy_downFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SubjectEditFive.setSelection(subject_list.indexOf(Monday.get(3).getSubjectEdit()));
                    AudienceEditFive.setSelection(audience_list.indexOf(Monday.get(3).getAudienceEdit()));
                    EducatorEditFive.setSelection(educator_list.indexOf(Monday.get(3).getEducator()));
                    switch (IdRadioButtonFour) {
                        case 0:
                            rb_lectureFive.setChecked(true);
                            break;
                        case 1:
                            rb_labworkFive.setChecked(true);
                            break;
                        case 2:
                            rb_practiceFive.setChecked(true);
                            break;
                    }
                } catch (NullPointerException e) {
                }
            }
        });

        copy_downFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SubjectEditSix.setSelection(subject_list.indexOf(Monday.get(4).getSubjectEdit()));
                    AudienceEditSix.setSelection(audience_list.indexOf(Monday.get(4).getAudienceEdit()));
                    EducatorEditSix.setSelection(educator_list.indexOf(Monday.get(4).getEducator()));
                    switch (IdRadioButtonFive) {
                        case 0:
                            rb_lectureSix.setChecked(true);
                            break;
                        case 1:
                            rb_labworkSix.setChecked(true);
                            break;
                        case 2:
                            rb_practiceSix.setChecked(true);
                            break;
                    }
                } catch (NullPointerException e) {
                }
            }
        });

        copy_upTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SubjectEditOne.setSelection(subject_list.indexOf(Monday.get(1).getSubjectEdit()));
                    AudienceEditOne.setSelection(audience_list.indexOf(Monday.get(1).getAudienceEdit()));
                    EducatorEditOne.setSelection(educator_list.indexOf(Monday.get(1).getEducator()));
                    switch (IdRadioButtonTwo) {
                        case 0:
                            rb_lecture.setChecked(true);
                            break;
                        case 1:
                            rb_labwork.setChecked(true);
                            break;
                        case 2:
                            rb_practice.setChecked(true);
                            break;
                    }
                } catch (NullPointerException e) {
                }
            }
        });

        copy_upThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SubjectEditTwo.setSelection(subject_list.indexOf(Monday.get(2).getSubjectEdit()));
                    AudienceEditTwo.setSelection(audience_list.indexOf(Monday.get(2).getAudienceEdit()));
                    EducatorEditTwo.setSelection(educator_list.indexOf(Monday.get(2).getEducator()));
                    switch (IdRadioButtonThree) {
                        case 0:
                            rb_lectureTwo.setChecked(true);
                            break;
                        case 1:
                            rb_labworkTwo.setChecked(true);
                            break;
                        case 2:
                            rb_practiceTwo.setChecked(true);
                            break;
                    }
                } catch (NullPointerException e) {
                }
            }
        });

        copy_upFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SubjectEditThree.setSelection(subject_list.indexOf(Monday.get(3).getSubjectEdit()));
                    AudienceEditThree.setSelection(audience_list.indexOf(Monday.get(3).getAudienceEdit()));
                    EducatorEditThree.setSelection(educator_list.indexOf(Monday.get(3).getEducator()));
                    switch (IdRadioButtonFour) {
                        case 0:
                            rb_lectureThree.setChecked(true);
                            break;
                        case 1:
                            rb_labworkThree.setChecked(true);
                            break;
                        case 2:
                            rb_practiceThree.setChecked(true);
                            break;
                    }
                } catch (NullPointerException e) {
                }
            }
        });

        copy_upFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SubjectEditFour.setSelection(subject_list.indexOf(Monday.get(4).getSubjectEdit()));
                    AudienceEditFour.setSelection(audience_list.indexOf(Monday.get(4).getAudienceEdit()));
                    EducatorEditFour.setSelection(educator_list.indexOf(Monday.get(4).getEducator()));
                    switch (IdRadioButtonFive) {
                        case 0:
                            rb_lectureFour.setChecked(true);
                            break;
                        case 1:
                            rb_labworkFour.setChecked(true);
                            break;
                        case 2:
                            rb_practiceFour.setChecked(true);
                            break;
                    }
                } catch (NullPointerException e) {
                }
            }
        });

        copy_upSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SubjectEditFive.setSelection(subject_list.indexOf(Monday.get(5).getSubjectEdit()));
                    AudienceEditFive.setSelection(audience_list.indexOf(Monday.get(5).getAudienceEdit()));
                    EducatorEditFive.setSelection(educator_list.indexOf(Monday.get(5).getEducator()));
                    switch (IdRadioButtonSix) {
                        case 0:
                            rb_lectureFive.setChecked(true);
                            break;
                        case 1:
                            rb_labworkFive.setChecked(true);
                            break;
                        case 2:
                            rb_practiceFive.setChecked(true);
                            break;
                    }
                } catch (NullPointerException e) {
                }
            }
        });


        clearOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SubjectEditOne.setSelection(0);
                AudienceEditOne.setSelection(0);
                EducatorEditOne.setSelection(0);
                typeEditOne_monday.clearCheck();
            }
        });

        clearTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditTwo.setSelection(0);
                AudienceEditTwo.setSelection(0);
                EducatorEditTwo.setSelection(0);
                typeEditTwo_monday.clearCheck();
            }
        });

        clearThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditThree.setSelection(0);
                AudienceEditThree.setSelection(0);
                EducatorEditThree.setSelection(0);
                typeEditThree_monday.clearCheck();
            }
        });

        clearFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditFour.setSelection(0);
                AudienceEditFour.setSelection(0);
                EducatorEditFour.setSelection(0);
                typeEditFour_monday.clearCheck();
            }
        });

        clearFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditFive.setSelection(0);
                AudienceEditFive.setSelection(0);
                EducatorEditFive.setSelection(0);
                typeEditFive_monday.clearCheck();
            }
        });

        clearSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectEditSix.setSelection(0);
                AudienceEditSix.setSelection(0);
                EducatorEditSix.setSelection(0);
                typeEditSix_monday.clearCheck();
            }
        });


        SubjectEditTwo.setSelection(subject_list.indexOf(Monday.get(0).getSubjectEdit().toString()));
        SubjectEditTwo.setSelection(subject_list.indexOf(Monday.get(1).getSubjectEdit().toString()));
        SubjectEditThree.setSelection(subject_list.indexOf(Monday.get(2).getSubjectEdit().toString()));
        SubjectEditFour.setSelection(subject_list.indexOf(Monday.get(3).getSubjectEdit().toString()));
        SubjectEditFive.setSelection(subject_list.indexOf(Monday.get(4).getSubjectEdit().toString()));
        SubjectEditSix.setSelection(subject_list.indexOf(Monday.get(5).getSubjectEdit().toString()));
        AudienceEditOne.setSelection(audience_list.indexOf(Monday.get(0).getAudienceEdit().toString()));
        AudienceEditTwo.setSelection(audience_list.indexOf(Monday.get(1).getAudienceEdit().toString()));
        AudienceEditThree.setSelection(audience_list.indexOf(Monday.get(2).getAudienceEdit().toString()));
        AudienceEditFour.setSelection(audience_list.indexOf(Monday.get(3).getAudienceEdit().toString()));
        AudienceEditFive.setSelection(audience_list.indexOf(Monday.get(4).getAudienceEdit().toString()));
        AudienceEditSix.setSelection(audience_list.indexOf(Monday.get(5).getAudienceEdit().toString()));
        EducatorEditOne.setSelection(educator_list.indexOf(Monday.get(0).getEducator().toString()));
        EducatorEditTwo.setSelection(educator_list.indexOf(Monday.get(1).getEducator().toString()));
        EducatorEditThree.setSelection(educator_list.indexOf(Monday.get(2).getEducator().toString()));
        EducatorEditFour.setSelection(educator_list.indexOf(Monday.get(3).getEducator().toString()));
        EducatorEditFive.setSelection(educator_list.indexOf(Monday.get(4).getEducator().toString()));
        EducatorEditSix.setSelection(educator_list.indexOf(Monday.get(5).getEducator().toString()));


        if (Monday.get(0).getTypeLesson().toString().equals(typelesson.get(0))) {
            rb_lecture.setChecked(true);
        } else if (Monday.get(0).getTypeLesson().toString().equals(typelesson.get(1))) {
            rb_labwork.setChecked(true);
        } else if (Monday.get(0).getTypeLesson().toString().equals(typelesson.get(2))) {
            rb_practice.setChecked(true);
        }
        if (Monday.get(1).getTypeLesson().toString().equals(typelesson.get(0))) {
            rb_lectureTwo.setChecked(true);
        } else if (Monday.get(1).getTypeLesson().toString().equals(typelesson.get(1))) {
            rb_labworkTwo.setChecked(true);
        } else if (Monday.get(1).getTypeLesson().toString().equals(typelesson.get(2))) {
            rb_practiceTwo.setChecked(true);
        }
        if (Monday.get(2).getTypeLesson().toString().equals(typelesson.get(0))) {
            rb_lectureThree.setChecked(true);
        } else if (Monday.get(2).getTypeLesson().toString().equals(typelesson.get(1))) {
            rb_labworkThree.setChecked(true);
        } else if (Monday.get(2).getTypeLesson().toString().equals(typelesson.get(2))) {
            rb_practiceThree.setChecked(true);
        }
        if (Monday.get(3).getTypeLesson().toString().equals(typelesson.get(0))) {
            rb_lectureFour.setChecked(true);
        } else if (Monday.get(3).getTypeLesson().toString().equals(typelesson.get(1))) {
            rb_labworkFour.setChecked(true);
        } else if (Monday.get(3).getTypeLesson().toString().equals(typelesson.get(2))) {
            rb_practiceFour.setChecked(true);
        }
        if (Monday.get(4).getTypeLesson().toString().equals(typelesson.get(0))) {
            rb_lectureFive.setChecked(true);
        } else if (Monday.get(4).getTypeLesson().toString().equals(typelesson.get(1))) {
            rb_labworkFive.setChecked(true);
        } else if (Monday.get(4).getTypeLesson().toString().equals(typelesson.get(2))) {
            rb_practiceFive.setChecked(true);
        }
        if (Monday.get(5).getTypeLesson().toString().equals(typelesson.get(0))) {
            rb_lectureSix.setChecked(true);
        } else if (Monday.get(5).getTypeLesson().toString().equals(typelesson.get(1))) {
            rb_labworkSix.setChecked(true);
        } else if (Monday.get(5).getTypeLesson().toString().equals(typelesson.get(2))) {
            rb_practiceSix.setChecked(true);
        }
        //S1
        try {
            SubjectEditOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    MondayValueSubjectOne = SubjectEditOne.getSelectedItem().toString();
                    Monday.set(0, new Lesson("1", "8:30-10:00", MondayValueSubjectOne, Monday.get(0).getAudienceEdit().toString(), Monday.get(0).getEducator().toString(), Monday.get(0).getTypeLesson().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } catch (NullPointerException e) {
        }

        //S2
        try {
            SubjectEditTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    MondayValueSubjectTwo = SubjectEditTwo.getSelectedItem().toString();
                    Monday.set(1, new Lesson("2", "10:10-11:40", MondayValueSubjectTwo, Monday.get(1).getAudienceEdit().toString(), Monday.get(1).getEducator().toString(), Monday.get(1).getTypeLesson().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (NullPointerException e) {
        }
        //S3
        try {
            SubjectEditThree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueSubjectThree = SubjectEditThree.getSelectedItem().toString();
                    Monday.set(2, new Lesson("3", "12:20-13:50", MondayValueSubjectThree, Monday.get(2).getAudienceEdit().toString(), Monday.get(2).getEducator().toString(), Monday.get(2).getTypeLesson().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (NullPointerException e) {
        }
        //S4
        try {
            SubjectEditFour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueSubjectFour = SubjectEditFour.getSelectedItem().toString();
                    Monday.set(3, new Lesson("4", "14:00-15:30", MondayValueSubjectFour, Monday.get(3).getAudienceEdit().toString(), Monday.get(3).getEducator().toString(), Monday.get(3).getTypeLesson().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (NullPointerException e) {
        }
        //S5
        try {
            SubjectEditFive.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueSubjectFive = SubjectEditFive.getSelectedItem().toString();
                    Monday.set(4, new Lesson("5", "15:40-17:10", MondayValueSubjectFive, Monday.get(4).getAudienceEdit().toString(), Monday.get(4).getEducator().toString(), Monday.get(4).getTypeLesson().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (NullPointerException e) {
        }
        //S6
        try {
            SubjectEditSix.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueSubjectSix = SubjectEditSix.getSelectedItem().toString();
                    Monday.set(5, new Lesson("6", "17:30-19:00", MondayValueSubjectSix, Monday.get(5).getAudienceEdit().toString(), Monday.get(5).getEducator().toString(), Monday.get(5).getTypeLesson().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (NullPointerException e) {
        }

        //A1
        try {
            AudienceEditOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueAudienceOne = AudienceEditOne.getSelectedItem().toString();
                    Monday.set(0, new Lesson("1", "8:30-10:00", Monday.get(0).getSubjectEdit().toString(), MondayValueAudienceOne, Monday.get(0).getEducator().toString(), Monday.get(0).getTypeLesson().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (NullPointerException e) {
        }
        //A2
        try {
            AudienceEditTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueAudienceTwo = AudienceEditTwo.getSelectedItem().toString();
                    Monday.set(1, new Lesson("2", "10:10-11:40", Monday.get(1).getSubjectEdit().toString(), MondayValueAudienceTwo, Monday.get(1).getEducator().toString(), Monday.get(1).getTypeLesson().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (NullPointerException e) {
        }

        //A3
        try {
            AudienceEditThree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueAudienceThree = AudienceEditThree.getSelectedItem().toString();
                    Monday.set(2, new Lesson("3", "12:20-13:50", Monday.get(2).getSubjectEdit().toString(), MondayValueAudienceThree, Monday.get(2).getEducator().toString(), Monday.get(2).getTypeLesson().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (NullPointerException e) {
        }
        //A4
        try {
            AudienceEditFour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueAudienceFour = AudienceEditFour.getSelectedItem().toString();
                    Monday.set(3, new Lesson("4", "14:00-15:30", Monday.get(3).getSubjectEdit().toString(), MondayValueAudienceFour, Monday.get(3).getEducator().toString(), Monday.get(3).getTypeLesson().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (NullPointerException e) {
        }
        //A5
        try {
            AudienceEditFive.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueAudienceFive = AudienceEditFive.getSelectedItem().toString();
                    Monday.set(4, new Lesson("5", "15:40-17:10", Monday.get(4).getSubjectEdit().toString(), MondayValueAudienceFive, Monday.get(4).getEducator().toString(), Monday.get(4).getTypeLesson().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (NullPointerException e) {
        }
        //A6
        try {
            AudienceEditSix.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayValueAudienceSix = AudienceEditSix.getSelectedItem().toString();
                    Monday.set(5, new Lesson("6", "17:30-19:00", Monday.get(5).getSubjectEdit().toString(), MondayValueAudienceSix, Monday.get(5).getEducator().toString(), Monday.get(5).getTypeLesson().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (NullPointerException e) {
        }

        //E1
        try {
            EducatorEditOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayEducatorOne = EducatorEditOne.getSelectedItem().toString();
                    Monday.set(0, new Lesson("1", "8:30-10:00", Monday.get(0).getSubjectEdit().toString(), Monday.get(0).getAudienceEdit().toString(), MondayEducatorOne, Monday.get(0).getTypeLesson().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (NullPointerException e) {
        }
        //E2
        try {
            EducatorEditTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayEducatorTwo = EducatorEditTwo.getSelectedItem().toString();
                    Monday.set(1, new Lesson("2", "10:10-11:40", Monday.get(1).getSubjectEdit().toString(), Monday.get(1).getAudienceEdit().toString(), MondayEducatorTwo, Monday.get(1).getTypeLesson().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (NullPointerException e) {
        }

        //E3
        try {
            EducatorEditThree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayEducatorThree = EducatorEditThree.getSelectedItem().toString();
                    Monday.set(2, new Lesson("3", "12:20-13:50", Monday.get(2).getSubjectEdit().toString(), Monday.get(2).getAudienceEdit().toString(), MondayEducatorThree, Monday.get(2).getTypeLesson().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (NullPointerException e) {
        }
        //E4
        try {
            EducatorEditFour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayEducatorFour = EducatorEditFour.getSelectedItem().toString();
                    Monday.set(3, new Lesson("4", "14:00-15:30", Monday.get(3).getSubjectEdit().toString(), Monday.get(3).getAudienceEdit().toString(), MondayEducatorFour, Monday.get(3).getTypeLesson().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (NullPointerException e) {
        }
        //E5
        try {
            EducatorEditFive.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayEducatorFive = EducatorEditFive.getSelectedItem().toString();
                    Monday.set(4, new Lesson("5", "15:40-17:10", Monday.get(4).getSubjectEdit().toString(), Monday.get(4).getAudienceEdit().toString(), MondayEducatorFive, Monday.get(4).getTypeLesson().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (NullPointerException e) {
        }
        //E6
        try {
            EducatorEditSix.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    MondayEducatorSix = EducatorEditSix.getSelectedItem().toString();
                    Monday.set(5, new Lesson("6", "17:30-19:00", Monday.get(5).getSubjectEdit().toString(), Monday.get(5).getAudienceEdit().toString(), MondayEducatorSix, Monday.get(5).getTypeLesson().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (NullPointerException e) {
        }
*/


        return fragmentView;
    }

/*    private void DataMonday() {
        Monday = new ArrayList<>();
        try {
            Monday.add(new Lesson("1", String.valueOf(calls_schedule.get(0)), MondayValueSubjectOne, MondayValueAudienceOne, MondayEducatorOne, MondayTypeLessonOne));
        } catch (NullPointerException e) {
        }
        try {
            Monday.add(new Lesson("2", String.valueOf(calls_schedule.get(1)), MondayValueSubjectTwo, MondayValueAudienceTwo, MondayEducatorTwo, MondayTypeLessonTwo));
        } catch (NullPointerException e) {
        }
        try {
            Monday.add(new Lesson("3", String.valueOf(calls_schedule.get(2)), MondayValueSubjectThree, MondayValueAudienceThree, MondayEducatorThree, MondayTypeLessonThree));
        } catch (NullPointerException e) {
        }
        try {
            Monday.add(new Lesson("4", String.valueOf(calls_schedule.get(3)), MondayValueSubjectFour, MondayValueAudienceFour, MondayEducatorFour, MondayTypeLessonFour));
        } catch (NullPointerException e) {
        }
        try {
            Monday.add(new Lesson("5", String.valueOf(calls_schedule.get(4)), MondayValueSubjectFive, MondayValueAudienceFive, MondayEducatorFive, MondayTypeLessonFive));
        } catch (NullPointerException e) {
        }
        try {
            Monday.add(new Lesson("6", String.valueOf(calls_schedule.get(5)), MondayValueSubjectSix, MondayValueAudienceSix, MondayEducatorSix, MondayTypeLessonSix));
        } catch (NullPointerException e) {
        }
    }*/
   /* void filling_array_list() {
        ScheduleDB = new ScheduleDB(this);
        SQLiteDatabase myDataBase = ScheduleDB.getReadableDatabase();
        String searchQuery = "SELECT " + ScheduleClass.subjects.subject + " FROM " + ScheduleClass.subjects.TABLE_NAME;
        Cursor cursor = myDataBase.rawQuery(searchQuery, null);
        while (cursor.moveToNext()) {
            subject_list.add(cursor.getString(0));
        }
        cursor.close();

        myDataBase = ScheduleDB.getReadableDatabase();
        searchQuery = "SELECT " + ScheduleClass.audiences.audience + " FROM " + ScheduleClass.audiences.TABLE_NAME;
        cursor = myDataBase.rawQuery(searchQuery, null);
        while (cursor.moveToNext()) {
            audience_list.add(cursor.getString(0));
        }
        cursor.close();

        myDataBase = ScheduleDB.getReadableDatabase();
        searchQuery = "SELECT " + ScheduleClass.educators.educator + " FROM " + ScheduleClass.educators.TABLE_NAME;
        cursor = myDataBase.rawQuery(searchQuery, null);
        while (cursor.moveToNext()) {
            educator_list.add(cursor.getString(0));
        }
        cursor.close();
    }*/


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}