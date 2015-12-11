package com.example.carlosantonio.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.*;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.carlosantonio.myapplication.models.MovieModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Antonio on 18/11/2015.
 */
public class TestHttp extends Activity {

    private TextView tvData;
    private ListView lvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_http2);

        //crear opcion por default cual sera utilizado cada vez
        //displayImage(...) llamar si no hay option sera pasado a este metodo

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions).build();

        ImageLoader.getInstance().init(config);

        lvMovies = (ListView) findViewById(R.id.lvMovies);

        //crear una configuracion global de inicializacion Imageloader con esta configuracion
        //ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        //ImageLoader.getInstance().init(config);
        //new JSONTask().execute("http://jsonparsing.parseapp.com/jsonData/moviesData.txt");
        //bteHit = (Button) findViewById(R.id.bteHit);
        //tvData = (TextView) findViewById(R.id.tvJsonItem);
        //bteHit.setOnClickListener(this);
        new JSONTask().execute("http://jsonparsing.parseapp.com/jsonData/moviesData.txt");
    }


    public class JSONTask extends AsyncTask<String, String, List<MovieModel> >{

        @Override
        protected List<MovieModel> doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                //Toma los valores del texto tomado en string
                String finalJSON = buffer.toString();

                //usa los datos en formato JSON
                JSONObject parentObject = new JSONObject(finalJSON);
                //toma el JSON Array
                JSONArray parentArray = parentObject.getJSONArray("movies");

                //StringBuffer finalBufferedData = new StringBuffer();

                List<MovieModel> movieModelList = new ArrayList<>();

                for (int i=0; i < parentArray.length(); i ++){

                    JSONObject finalObject = parentArray.getJSONObject(i);
                    MovieModel movieModel = new MovieModel();
                    movieModel.setMovie(finalObject.getString("movie"));
                    movieModel.setYear(finalObject.getInt("year"));
                    movieModel.setRating((float) finalObject.getDouble("rating"));
                    movieModel.setDirector(finalObject.getString("director"));
                    movieModel.setDuration(finalObject.getString("duration"));
                    movieModel.setTagline(finalObject.getString("tagline"));
                    movieModel.setImage(finalObject.getString("image"));
                    movieModel.setStory(finalObject.getString("story"));

                    List<MovieModel.Cast> castList = new ArrayList<>();


                    for(int j = 0; j < finalObject.getJSONArray("cast").length(); j ++){

                        MovieModel.Cast cast = new MovieModel.Cast();
                        cast.setName(finalObject.getJSONArray("cast").getJSONObject(j).getString("name"));
                        castList.add(cast);

                    }

                    movieModel.setCastList(castList);
                    //agregar el objeto final en la lista
                    movieModelList.add(movieModel);


                    //String movieName = finalObject.getString("movie");
                    //int year = finalObject.getInt("year");
                    //finalBufferedData.append(movieName + " - "+ year + "\n");

                }


                /*JSONObject finalObject = parentArray.getJSONObject(0);

                String movieName = finalObject.getString("movie");
                int year = finalObject.getInt("year");*/

                return movieModelList;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection != null) {
                    connection.disconnect();
                }
                try {
                    if(reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<MovieModel> result) {
            super.onPostExecute(result);

            MovieAdapter adapter = new MovieAdapter(getApplicationContext(), R.layout.row,result);
            lvMovies.setAdapter(adapter);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_refresh){
            new JSONTask().execute("http://jsonparsing.parseapp.com/jsonData/moviesData.txt");
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public class MovieAdapter extends ArrayAdapter{

        private List<MovieModel> movieModelList;
        private int resource;
        private LayoutInflater inflator;

        public MovieAdapter(Context context, int resource, List<MovieModel> objects) {
            super(context, resource, objects);
            movieModelList = objects;
            //el layout
            this.resource = resource;
            inflator = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if(convertView == null) {
                holder = new ViewHolder();
                convertView = inflator.inflate(resource, null);
                holder.ivMovieIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
                holder.tvMovie = (TextView) convertView.findViewById(R.id.tvMovie);
                holder.tvTagline = (TextView) convertView.findViewById(R.id.tvTagline);
                holder.tvYear = (TextView) convertView.findViewById(R.id.tvYear);
                holder.tvDuration = (TextView) convertView.findViewById(R.id.tvDuration);
                holder.tvDirector = (TextView) convertView.findViewById(R.id.tvDirector);
                holder.rbMovieRating = (RatingBar) convertView.findViewById(R.id.rbMovie);
                holder.tvCast = (TextView) convertView.findViewById(R.id.tvCast);
                holder.tvStory = (TextView) convertView.findViewById(R.id.tvStory);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            final ProgressBar progressbar = (ProgressBar) convertView.findViewById(R.id.progressBar);

            //entonces cuando tu quieres mostrar una imagen
            ImageLoader.getInstance().displayImage(movieModelList.get(position).getImage(), holder.ivMovieIcon, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressbar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressbar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressbar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    progressbar.setVisibility(View.GONE);
                }
            });

            holder.tvMovie.setText(movieModelList.get(position).getMovie());
            holder.tvTagline.setText(movieModelList.get(position).getTagline());
            holder.tvYear.setText("Year: " + movieModelList.get(position).getYear());
            holder.tvDuration.setText("Duration: " + movieModelList.get(position).getDuration());
            holder.tvDirector.setText("Director: " + movieModelList.get(position).getDirector());

            holder.rbMovieRating.setRating(movieModelList.get(position).getRating()/2);


            StringBuffer stringbuffer = new StringBuffer();
            for (MovieModel.Cast cast: movieModelList.get(position).getCastList()){
                stringbuffer.append(cast.getName() + ", ");
            }

            holder.tvCast.setText("Cast: " + stringbuffer);
            holder.tvStory.setText(movieModelList.get(position).getStory());


            return convertView;
        }

        class ViewHolder{
            private ImageView ivMovieIcon;
            private TextView tvMovie;
            private TextView tvTagline;
            private TextView tvYear;
            private TextView tvDuration;
            private TextView tvDirector;
            private RatingBar rbMovieRating;
            private TextView tvCast;
            private TextView tvStory;


        }


    }



}


