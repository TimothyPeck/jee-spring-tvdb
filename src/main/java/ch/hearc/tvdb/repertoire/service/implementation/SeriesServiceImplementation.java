package ch.hearc.tvdb.repertoire.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.hearc.tvdb.repertoire.model.Series;
import ch.hearc.tvdb.repertoire.repository.SeriesRepository;
import ch.hearc.tvdb.repertoire.service.SeriesService;

@Service
public class SeriesServiceImplementation implements SeriesService {

    @Autowired
    private SeriesRepository seriesRepository;

    @Override
    public void addSeries(Series series) {
        seriesRepository.save(series);
    }

    @Override
    public List<Series> getAllSeries() {
        List<Series> series = new ArrayList<Series>();
        seriesRepository.findAll().forEach(series::add);
        return series;
    }

    @Override
    public Series getSeriesById(Long id) {
        return seriesRepository.findById(id).get();
    }

    @Override
    public void deleteSeriesById(Long id) {
        seriesRepository.deleteById(id);
    }

    @Override
    public Series updateSeries(Series series) {
        seriesRepository.save(series);
        return series;
    }

    @Override
    public void deleteSeries(Series series) {
        seriesRepository.delete(series);
    }

}
