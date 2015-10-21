package lolchatter.com.moxom.lolchatter;

        import android.app.ProgressDialog;
        import android.os.AsyncTask;
        import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
        import android.util.Log;
        import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

//fragment imports

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


        import org.jivesoftware.smack.AbstractXMPPConnection;
        import org.jivesoftware.smack.ConnectionConfiguration;
        import org.jivesoftware.smack.SmackException;
        import org.jivesoftware.smack.XMPPException;
        import org.jivesoftware.smack.tcp.XMPPTCPConnection;
        import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

        import java.io.IOException;
        import java.security.KeyManagementException;
        import java.security.NoSuchAlgorithmException;
        import java.security.SecureRandom;

        import javax.net.ssl.SSLContext;
        import javax.net.ssl.SSLSocketFactory;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager mViewPager;
    private MainActivity.SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //fragment


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        new AsyncCaller().execute();



        /*
        final LolChat api = new LolChat(ChatServer.NA2, FriendRequestPolicy.ACCEPT_ALL, new RiotApiKey("RIOT-API-KEY", RateLimit.DEFAULT));
        if (api.login("cousinfartbubbles", "cousinfartbubbles")) {

            // Example 1: Send Chat Message to all your friends
            for (Friend f : api.getFriends()) {
                Log.i("LOL", "FRIEND" + f.getName());
              //  f.sendMessage("Hello " + f.getName());
            }

            // Example 2: Send Chat Message to all your friends and wait for an
            // response
            for (Friend f : api.getFriends()) {
                f.sendMessage("Hello " + f.getName(), new ChatListener() {

                    @Override
                    public void onMessage(Friend friend, String message) {
                   //     System.out.println("Friend " + friend.getName()
                 //               + " responded to my Hello World!");
                    }
                });
            }

            // Example3: Send Chat Message to an specific friend
            Friend f = api.getFriendByName("Dyrus");
            if (f != null && f.isOnline()) {
                f.sendMessage("Hi, I'm your biggest fan!");
            }
        }
*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }


    private class AsyncCaller extends AsyncTask<Void, Void, Void> {
        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.show();
        }

        @Override
        protected Void doInBackground(Void... params) {


            try {


             /*
                 ConnectionConfiguration config = new ConnectionConfiguration("chat.na2.lol.riotgames.com", 5223,);
                config.setSecurityMode(ConnectionConfiguration.SecurityMode.enabled);
                config.setSocketFactory(SSLSocketFactory.getDefault());
                config.setCompressionEnabled(true);
                connection = new XMPPTCPConnection(config);
*/
/*


                int abc = 5;
                XMPPTCPConnectionConfiguration config2 = XMPPTCPConnectionConfiguration.builder()
                        .setUsernameAndPassword("username", "AIR_password")
                        .setResource("xiff")
                        .setHost("chat.na2.lol.riotgames.com")
                        .setPort(5223)
                        .setServiceName("pvp.net")
                                .setSecurityMode(ConnectionConfiguration.SecurityMode.required)
                      //  .setSecurityMode(ConnectionConfiguration.SecurityMode.ifpossible)
                       .setSocketFactory(SSLSocketFactory.getDefault())
                        .setCompressionEnabled(true)
                        .build();

                AbstractXMPPConnection conn2 = new XMPPTCPConnection(config2);
                conn2.connect();
*/
/*
                XMPPTCPConnectionConfiguration.Builder config = XMPPTCPConnectionConfiguration.builder();
                config.setSecurityMode(ConnectionConfiguration.SecurityMode.required);
                //For OLD STYLE SSL
                config.setSecurityMode(ConnectionConfiguration.SecurityMode.enabled);
                config.setUsernameAndPassword("username" + "@" + "pvp.net", "AIR_"+password);
                config.setServiceName("pvp.net");
                config.setHost(chat.na2.lol.riotgames.com);
                config.setPort(5223);
                config.setDebuggerEnabled(true);
                //OLD STYLE SSL
               
                config.setSocketFactory(SSLSocketFactory.getDefault());

                /*
                try {
                    SSLContext sc = SSLContext.getInstance("TLS");
                    MemorizingTrustManager mtm = new MemorizingTrustManager(getBaseContext());
                    sc.init(null, MemorizingTrustManager.getInstanceList(ctx), new SecureRandom());
                    config.setCustomSSLContext(sc);
                    config.setHostnameVerifier(mtm.wrapHostnameVerifier(new org.apache.http.conn.ssl.StrictHostnameVerifier()));
                } catch (NoSuchAlgorithmException | KeyManagementException e) {
                    throw new IllegalStateException(e);
                }
*/
                XMPPTCPConnection  mConnection = new XMPPTCPConnection(config.build());
                mConnection.setPacketReplyTimeout(10000);

                try {
                    mConnection.connect();
                    mConnection.login();
                } catch (SmackException | IOException | XMPPException e) {
                    e.printStackTrace();
                }

                int a = 5;
            } catch (Exception e) {

                int a = 5;
                int b = 5;
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //this method will be running on UI thread

            pdLoading.dismiss();
        }
    }
}
