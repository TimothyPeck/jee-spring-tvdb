package ch.hearc.tvdb.repertoire.service;

import java.util.List;

import ch.hearc.tvdb.repertoire.model.Series;

public interface SeriesService {
    public void addSeries(Series series);

    public List<Series> getAllSeries();

    public Series getSeriesById(Long id);

    public void deleteSeriesById(Long id);

    public Series updateSeries(Series series);
}
