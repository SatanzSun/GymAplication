package com.example.mysqlproject;

public class Exercise {
    int _id;
    String _exercise;
    int _set;
    int _reps;
    double _kg;
    public Exercise(){   }
    public Exercise(int id, String exercise, int setnum, int reps, double kg){
        this._id = id;
        this._exercise = exercise;
        this._set = setnum;
        this._reps = reps;
        this._kg = kg;
    }

    public Exercise(String exercise, int setnum, int reps, double kg){
        this._exercise = exercise;
        this._set = setnum;
        this._reps = reps;
        this._kg = kg;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_exercise() {
        return _exercise;
    }

    public void set_exercise(String _exercise) {
        this._exercise = _exercise;
    }

    public int get_set() {
        return _set;
    }

    public void set_set(int _set) {
        this._set = _set;
    }

    public int get_reps() {
        return _reps;
    }

    public void set_reps(int _reps) {
        this._reps = _reps;
    }

    public double get_kg() {
        return _kg;
    }

    public void set_kg(double _kg) {
        this._kg = _kg;
    }

}
