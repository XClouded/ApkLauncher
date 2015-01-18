package org.bbs.apklauncher;

import java.util.List;

import org.bbs.felix.util.ApkManifestParser.PackageInfoX.ApplicationInfoX;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.util.Log;

public class PackageManagerProxy extends PackageManager {
	
	private static final String TAG = PackageManagerProxy.class.getSimpleName();
	private PackageManager mProxy;

	public PackageManagerProxy(PackageManager policy) {
		mProxy = policy;
	}

	@Override
	public PackageInfo getPackageInfo(String packageName, int flags)
			throws NameNotFoundException {
		return mProxy.getPackageInfo(packageName, flags);
	}

	@Override
	public String[] currentToCanonicalPackageNames(String[] names) {
		return mProxy.currentToCanonicalPackageNames(names);
	}

	@Override
	public String[] canonicalToCurrentPackageNames(String[] names) {
		return mProxy.canonicalToCurrentPackageNames(names);
	}

	@Override
	public Intent getLaunchIntentForPackage(String packageName) {
		return mProxy.getLaunchIntentForPackage(packageName);
	}

	@Override
	public Intent getLeanbackLaunchIntentForPackage(String packageName) {
		return mProxy.getLeanbackLaunchIntentForPackage(packageName);
	}

	@Override
	public int[] getPackageGids(String packageName)
			throws NameNotFoundException {
		return mProxy.getPackageGids(packageName);
	}

	@Override
	public PermissionInfo getPermissionInfo(String name, int flags)
			throws NameNotFoundException {
		return mProxy.getPermissionInfo(name, flags);
	}

	@Override
	public List<PermissionInfo> queryPermissionsByGroup(String group,
			int flags) throws NameNotFoundException {
		return mProxy.queryPermissionsByGroup(group, flags);
	}

	@Override
	public PermissionGroupInfo getPermissionGroupInfo(String name, int flags)
			throws NameNotFoundException {
		return mProxy.getPermissionGroupInfo(name, flags);
	}

	@Override
	public List<PermissionGroupInfo> getAllPermissionGroups(int flags) {
		return mProxy.getAllPermissionGroups(flags);
	}

	@Override
	public ApplicationInfo getApplicationInfo(String packageName, int flags)
			throws NameNotFoundException {
		Log.d(TAG, "getApplicationInfo(). packageName: " + packageName + " flags: " + flags);
		InstalledAPks apks = InstalledAPks.getInstance();
		if (apks.hasApplication(packageName)
				&& (flags | PackageManager.GET_META_DATA) != 0) {
			ApplicationInfoX aInfo = apks.getApplication(packageName);

			Log.d(TAG, "use pased ApplicationInfoX: " + aInfo);
			return aInfo;
		}
		
		return mProxy.getApplicationInfo(packageName, flags);
	}

	@Override
	public ActivityInfo getActivityInfo(ComponentName component, int flags)
			throws NameNotFoundException {
		return mProxy.getActivityInfo(component, flags);
	}

	@Override
	public ActivityInfo getReceiverInfo(ComponentName component, int flags)
			throws NameNotFoundException {
		return mProxy.getReceiverInfo(component, flags);
	}

	@Override
	public ServiceInfo getServiceInfo(ComponentName component, int flags)
			throws NameNotFoundException {
		return mProxy.getServiceInfo(component, flags);
	}

	@Override
	public ProviderInfo getProviderInfo(ComponentName component, int flags)
			throws NameNotFoundException {
		return mProxy.getProviderInfo(component, flags);
	}

	@Override
	public List<PackageInfo> getInstalledPackages(int flags) {
		return mProxy.getInstalledPackages(flags);
	}

	@Override
	public List<PackageInfo> getPackagesHoldingPermissions(
			String[] permissions, int flags) {
		return mProxy.getPackagesHoldingPermissions(permissions, flags);
	}

	@Override
	public int checkPermission(String permName, String pkgName) {
		return mProxy.checkPermission(permName, pkgName);
	}

	@Override
	public boolean addPermission(PermissionInfo info) {
		return mProxy.addPermission(info);
	}

	@Override
	public boolean addPermissionAsync(PermissionInfo info) {
		return mProxy.addPermission(info);
	}

	@Override
	public void removePermission(String name) {
		mProxy.removePermission(name);
	}

	@Override
	public int checkSignatures(String pkg1, String pkg2) {
		return mProxy.checkSignatures(pkg1, pkg2);
	}

	@Override
	public int checkSignatures(int uid1, int uid2) {
		return mProxy.checkSignatures(uid1, uid2);
	}

	@Override
	public String[] getPackagesForUid(int uid) {
		return mProxy.getPackagesForUid(uid);
	}

	@Override
	public String getNameForUid(int uid) {
		return mProxy.getNameForUid(uid);
	}

	@Override
	public List<ApplicationInfo> getInstalledApplications(int flags) {
		return mProxy.getInstalledApplications(flags);
	}

	@Override
	public String[] getSystemSharedLibraryNames() {
		return mProxy.getSystemSharedLibraryNames();
	}

	@Override
	public FeatureInfo[] getSystemAvailableFeatures() {
		return mProxy.getSystemAvailableFeatures();
	}

	@Override
	public boolean hasSystemFeature(String name) {
		return mProxy.hasSystemFeature(name);
	}

	@Override
	public ResolveInfo resolveActivity(Intent intent, int flags) {
		return mProxy.resolveActivity(intent, flags);
	}

	@Override
	public List<ResolveInfo> queryIntentActivities(Intent intent, int flags) {
		return mProxy.queryIntentActivities(intent, flags);
	}

	@Override
	public List<ResolveInfo> queryIntentActivityOptions(
			ComponentName caller, Intent[] specifics, Intent intent,
			int flags) {
		return mProxy.queryIntentActivityOptions(caller, specifics, intent, flags);
	}

	@Override
	public List<ResolveInfo> queryBroadcastReceivers(Intent intent,
			int flags) {
		return mProxy.queryBroadcastReceivers(intent, flags);
	}

	@Override
	public ResolveInfo resolveService(Intent intent, int flags) {
		return mProxy.resolveService(intent, flags);
	}

	@Override
	public List<ResolveInfo> queryIntentServices(Intent intent, int flags) {
		return mProxy.queryIntentServices(intent, flags);
	}

	@Override
	public List<ResolveInfo> queryIntentContentProviders(Intent intent,
			int flags) {
		return mProxy.queryIntentContentProviders(intent, flags);
	}

	@Override
	public ProviderInfo resolveContentProvider(String name, int flags) {
		return mProxy.resolveContentProvider(name, flags);
	}

	@Override
	public List<ProviderInfo> queryContentProviders(String processName,
			int uid, int flags) {
		return mProxy.queryContentProviders(processName, uid, flags);
	}

	@Override
	public InstrumentationInfo getInstrumentationInfo(
			ComponentName className, int flags)
			throws NameNotFoundException {
		return mProxy.getInstrumentationInfo(className, flags);
	}

	@Override
	public List<InstrumentationInfo> queryInstrumentation(
			String targetPackage, int flags) {
		return mProxy.queryInstrumentation(targetPackage, flags);
	}

	@Override
	public Drawable getDrawable(String packageName, int resid,
			ApplicationInfo appInfo) {
		return mProxy.getDrawable(packageName, resid, appInfo);
	}

	@Override
	public Drawable getActivityIcon(ComponentName activityName)
			throws NameNotFoundException {
		return mProxy.getActivityIcon(activityName);
	}

	@Override
	public Drawable getActivityIcon(Intent intent)
			throws NameNotFoundException {
		return mProxy.getActivityIcon(intent);
	}

	@Override
	public Drawable getActivityBanner(ComponentName activityName)
			throws NameNotFoundException {
		return mProxy.getActivityBanner(activityName);
	}

	@Override
	public Drawable getActivityBanner(Intent intent)
			throws NameNotFoundException {
		return mProxy.getActivityBanner(intent);
	}

	@Override
	public Drawable getDefaultActivityIcon() {
		return mProxy.getDefaultActivityIcon();
	}

	@Override
	public Drawable getApplicationIcon(ApplicationInfo info) {
		return mProxy.getApplicationIcon(info);
	}

	@Override
	public Drawable getApplicationIcon(String packageName)
			throws NameNotFoundException {
		return mProxy.getApplicationIcon(packageName);
	}

	@Override
	public Drawable getApplicationBanner(ApplicationInfo info) {
		return mProxy.getApplicationBanner(info);
	}

	@Override
	public Drawable getApplicationBanner(String packageName)
			throws NameNotFoundException {
		return mProxy.getApplicationBanner(packageName);
	}

	@Override
	public Drawable getActivityLogo(ComponentName activityName)
			throws NameNotFoundException {
		return mProxy.getActivityLogo(activityName);
	}

	@Override
	public Drawable getActivityLogo(Intent intent)
			throws NameNotFoundException {
		return mProxy.getActivityLogo(intent);
	}

	@Override
	public Drawable getApplicationLogo(ApplicationInfo info) {
		return mProxy.getApplicationBanner(info);
	}

	@Override
	public Drawable getApplicationLogo(String packageName)
			throws NameNotFoundException {
		return mProxy.getApplicationIcon(packageName);
	}

	@Override
	public Drawable getUserBadgedIcon(Drawable icon, UserHandle user) {
		return mProxy.getUserBadgedIcon(icon, user);
	}

	@Override
	public Drawable getUserBadgedDrawableForDensity(Drawable drawable,
			UserHandle user, Rect badgeLocation, int badgeDensity) {
		return mProxy.getUserBadgedDrawableForDensity(drawable, user, badgeLocation, badgeDensity);
	}

	@Override
	public CharSequence getUserBadgedLabel(CharSequence label,
			UserHandle user) {
		return mProxy.getUserBadgedLabel(label, user);
	}

	@Override
	public CharSequence getText(String packageName, int resid,
			ApplicationInfo appInfo) {
		return mProxy.getText(packageName, resid, appInfo);
	}

	@Override
	public XmlResourceParser getXml(String packageName, int resid,
			ApplicationInfo appInfo) {
		return mProxy.getXml(packageName, resid, appInfo);
	}

	@Override
	public CharSequence getApplicationLabel(ApplicationInfo info) {
		return mProxy.getApplicationLabel(info);
	}

	@Override
	public Resources getResourcesForActivity(ComponentName activityName)
			throws NameNotFoundException {
		return mProxy.getResourcesForActivity(activityName);
	}

	@Override
	public Resources getResourcesForApplication(ApplicationInfo app)
			throws NameNotFoundException {
		return mProxy.getResourcesForApplication(app);
	}

	@Override
	public Resources getResourcesForApplication(String appPackageName)
			throws NameNotFoundException {
		return mProxy.getResourcesForApplication(appPackageName);
	}

	@Override
	public void verifyPendingInstall(int id, int verificationCode) {
		mProxy.verifyPendingInstall(id, verificationCode);
	}

	@Override
	public void extendVerificationTimeout(int id,
			int verificationCodeAtTimeout, long millisecondsToDelay) {
		mProxy.extendVerificationTimeout(id, verificationCodeAtTimeout, millisecondsToDelay);
	}

	@Override
	public void setInstallerPackageName(String targetPackage,
			String installerPackageName) {
		mProxy.setInstallerPackageName(targetPackage, installerPackageName);
	}

	@Override
	public String getInstallerPackageName(String packageName) {
		return mProxy.getInstallerPackageName(packageName);
	}

	@Override
	public void addPackageToPreferred(String packageName) {
		mProxy.addPackageToPreferred(packageName);
	}

	@Override
	public void removePackageFromPreferred(String packageName) {
		mProxy.removePackageFromPreferred(packageName);
	}

	@Override
	public List<PackageInfo> getPreferredPackages(int flags) {
		return mProxy.getPreferredPackages(flags);
	}

	@Override
	public void addPreferredActivity(IntentFilter filter, int match,
			ComponentName[] set, ComponentName activity) {
		mProxy.addPreferredActivity(filter, match, set, activity);
	}

	@Override
	public void clearPackagePreferredActivities(String packageName) {
		mProxy.clearPackagePreferredActivities(packageName);
	}

	@Override
	public int getPreferredActivities(List<IntentFilter> outFilters,
			List<ComponentName> outActivities, String packageName) {
		return mProxy.getPreferredActivities(outFilters, outActivities, packageName);
	}

	@Override
	public void setComponentEnabledSetting(ComponentName componentName,
			int newState, int flags) {
		mProxy.setComponentEnabledSetting(componentName, newState, flags);
	}

	@Override
	public int getComponentEnabledSetting(ComponentName componentName) {
		return mProxy.getComponentEnabledSetting(componentName);
	}

	@Override
	public PackageInfo getPackageArchiveInfo(String archiveFilePath,
			int flags) {
		return mProxy.getPackageArchiveInfo(archiveFilePath, flags);
	}

	@Override
	public void setApplicationEnabledSetting(String packageName,
			int newState, int flags) {
		mProxy.setApplicationEnabledSetting(packageName, newState, flags);
	}

	@Override
	public int getApplicationEnabledSetting(String packageName) {
		return mProxy.getApplicationEnabledSetting(packageName);
	}

	@Override
	public boolean isSafeMode() {
		return mProxy.isSafeMode();
	}

	@Override
	public PackageInstaller getPackageInstaller() {
		return mProxy.getPackageInstaller();
	}
	
}