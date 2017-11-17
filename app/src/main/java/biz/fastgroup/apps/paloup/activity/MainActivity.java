package biz.fastgroup.apps.paloup.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.squareup.picasso.Picasso;

import java.io.IOException;


import biz.fastgroup.apps.paloup.R;
import biz.fastgroup.apps.paloup.controllers.UserController;
import biz.fastgroup.apps.paloup.databinding.FragmentMypaloBinding;
import biz.fastgroup.apps.paloup.fragment.EventFragment;
import biz.fastgroup.apps.paloup.fragment.HomeFragment;
import biz.fastgroup.apps.paloup.fragment.MypaloFragment;
import biz.fastgroup.apps.paloup.fragment.SettingsFragment;
import biz.fastgroup.apps.paloup.models.MyPaloViewType;
import biz.fastgroup.apps.paloup.models.UserModel;

import biz.fastgroup.apps.paloup.utils.Procedure;
import de.hdodenhof.circleimageview.CircleImageView;
/*import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;*/


public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
    private FloatingActionButton fab;
   // static Realm realm;
    private Procedure procedure;
    private CircleImageView imgProfile;
    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_MYPALO = "mypalo";
    private static final String TAG_MYEVENT = "myevent";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_HOME;
    static Context context;
    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    int badgeCount = 20;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mHandler = new Handler();
   //     this.realm = UserRealm.with(this).getRealm();


        context = this;
        try {

            procedure = new Procedure(this);

            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            navigationView = (NavigationView) findViewById(R.id.nav_view);
            fab = (FloatingActionButton) findViewById(R.id.fab);

            // Navigation view header
            navHeader = navigationView.getHeaderView(0);
            txtName = (TextView) navHeader.findViewById(R.id.name);
            txtWebsite = (TextView) navHeader.findViewById(R.id.website);
            imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
            imgProfile = (CircleImageView) navHeader.findViewById(R.id.img_profile);

     /*       if (!UserRealm.with(this).hasUser()) {
                Intent n = new Intent(this, LoginActivity.class);
                this.startActivity(n);
                finish();
            }*/

            // load toolbar titles from string resources
            activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            // load nav menu header data
            loadNavHeader();

            // initializing navigation menu
            setUpNavigationView();

            if (savedInstanceState == null) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
            }

            setMenuItemIcon();
        } catch (Exception err) {
            Toast.makeText(this, err.getMessage(), Toast.LENGTH_LONG);
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
    private void loadNavHeader() throws IOException {
        // name, website
       // UserModel userModel = realm.where(UserModel.class).findFirst();
      /*  txtName.setText(userModel.getFirstname() + ' ' + userModel.getLastname());
        txtWebsite.setText(userModel.getEmailaddress());
        String empId=userModel.getEmplId();
        Picasso.with(context).load("http://apps.fastgroup.biz/201pic/48px/" + empId + ".jpg")
              .placeholder(R.drawable.ic_profile).error(R.drawable.ic_profile).noFade().into(imgProfile);

        Bitmap bm = procedure.getBitmapFromAsset("nav-menu-header-bg.jpg");
        imgNavHeaderBg.setImageBitmap(bm);
*/
    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            // show or hide the fab button
            toggleFab();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
        toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                // mypalo

                MypaloFragment mypaloFragment = new MypaloFragment();

                return mypaloFragment;
            case 2:
                // event fragment
                EventFragment eventFragment = new EventFragment();
                return eventFragment;
            case 3:
                // settings fragment
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;

            default:
                return new HomeFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_mypalo:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_MYPALO;

                        break;
                    case R.id.nav_myevent:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_MYEVENT;
                        break;
                    case R.id.nav_settings:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;
                    case R.id.nav_about_us:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_privacy_policy:
                        //    launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
                        drawer.closeDrawers();
                        return true;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    private void setMenuItemIcon() {

        navigationView.getMenu().findItem(R.id.nav_home).setIcon(new IconicsDrawable(this).icon(MaterialDesignIconic.Icon.gmi_home));
        navigationView.getMenu().findItem(R.id.nav_mypalo).setIcon(new IconicsDrawable(this).icon(MaterialDesignIconic.Icon.gmi_assignment));
        navigationView.getMenu().findItem(R.id.nav_myevent).setIcon(new IconicsDrawable(this).icon(MaterialDesignIconic.Icon.gmi_calendar_alt));
        navigationView.getMenu().findItem(R.id.nav_settings).setIcon(new IconicsDrawable(this).icon(MaterialDesignIconic.Icon.gmi_settings));

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //you can add some logic (hide it if the count == 0)


        menu.findItem(R.id.action_search).setIcon(new IconicsDrawable(this).icon(MaterialDesignIconic.Icon.gmi_search).color(Color.WHITE).actionBar());
        // menu.findItem(R.id.action_notification).setIcon(new IconicsDrawable(this).icon(MaterialDesignIconic.Icon.gmi_notifications).color(Color.WHITE).actionBar());

        if (badgeCount > 0) {
            ActionItemBadge.update(this, menu.findItem(R.id.action_notification), MaterialDesignIconic.Icon.gmi_notifications, ActionItemBadge.BadgeStyles.YELLOW, badgeCount);
        } else {
            ActionItemBadge.hide(menu.findItem(R.id.action_notification));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {

            dialogLogOut(this);
            return true;

        } else if (id == R.id.action_notification) {
            startActivity(new Intent(MainActivity.this, NotificationActivity.class));
            badgeCount--;
            invalidateOptionsMenu();
            drawer.closeDrawers();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // show or hide the fab
    private void toggleFab() {
        if (navItemIndex == 0)
            fab.show();
        else
            fab.hide();
    }


    public static void dialogLogOut(Activity a) {
        final Activity mainActivity = a;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Log Out");
        builder.setMessage("Proceed to Log Out?");
        builder.setPositiveButton("Log Out",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                      /*  realm.beginTransaction();
                        realm.clear(UserModel.class);
                        realm.commitTransaction();
*/
                        dialog.dismiss();
                        mainActivity.finish();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                }
        );
    //   AlertDialog alert = builder.create();
        builder.show();
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}


