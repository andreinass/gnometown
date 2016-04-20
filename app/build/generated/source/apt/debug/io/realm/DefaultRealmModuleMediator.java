package io.realm;


import android.util.JsonReader;
import io.realm.internal.ColumnInfo;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Table;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import com.app.gnometown.Model.Gnome;
import com.app.gnometown.Model.RealmString;

@io.realm.annotations.RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmObject>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmObject>> modelClasses = new HashSet<Class<? extends RealmObject>>();
        modelClasses.add(RealmString.class);
        modelClasses.add(Gnome.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public Table createTable(Class<? extends RealmObject> clazz, ImplicitTransaction transaction) {
        checkClass(clazz);

        if (clazz.equals(RealmString.class)) {
            return RealmStringRealmProxy.initTable(transaction);
        } else if (clazz.equals(Gnome.class)) {
            return GnomeRealmProxy.initTable(transaction);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public ColumnInfo validateTable(Class<? extends RealmObject> clazz, ImplicitTransaction transaction) {
        checkClass(clazz);

        if (clazz.equals(RealmString.class)) {
            return RealmStringRealmProxy.validateTable(transaction);
        } else if (clazz.equals(Gnome.class)) {
            return GnomeRealmProxy.validateTable(transaction);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public List<String> getFieldNames(Class<? extends RealmObject> clazz) {
        checkClass(clazz);

        if (clazz.equals(RealmString.class)) {
            return RealmStringRealmProxy.getFieldNames();
        } else if (clazz.equals(Gnome.class)) {
            return GnomeRealmProxy.getFieldNames();
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public String getTableName(Class<? extends RealmObject> clazz) {
        checkClass(clazz);

        if (clazz.equals(RealmString.class)) {
            return RealmStringRealmProxy.getTableName();
        } else if (clazz.equals(Gnome.class)) {
            return GnomeRealmProxy.getTableName();
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmObject> E newInstance(Class<E> clazz, ColumnInfo columnInfo) {
        checkClass(clazz);

        if (clazz.equals(RealmString.class)) {
            return clazz.cast(new RealmStringRealmProxy(columnInfo));
        } else if (clazz.equals(Gnome.class)) {
            return clazz.cast(new GnomeRealmProxy(columnInfo));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public Set<Class<? extends RealmObject>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmObject> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmObject, RealmObjectProxy> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(RealmString.class)) {
            return clazz.cast(RealmStringRealmProxy.copyOrUpdate(realm, (RealmString) obj, update, cache));
        } else if (clazz.equals(Gnome.class)) {
            return clazz.cast(GnomeRealmProxy.copyOrUpdate(realm, (Gnome) obj, update, cache));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmObject> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        checkClass(clazz);

        if (clazz.equals(RealmString.class)) {
            return clazz.cast(RealmStringRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(Gnome.class)) {
            return clazz.cast(GnomeRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmObject> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        checkClass(clazz);

        if (clazz.equals(RealmString.class)) {
            return clazz.cast(RealmStringRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(Gnome.class)) {
            return clazz.cast(GnomeRealmProxy.createUsingJsonStream(realm, reader));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmObject> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmObject, RealmObjectProxy.CacheData<RealmObject>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(RealmString.class)) {
            return clazz.cast(RealmStringRealmProxy.createDetachedCopy((RealmString) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(Gnome.class)) {
            return clazz.cast(GnomeRealmProxy.createDetachedCopy((Gnome) realmObject, 0, maxDepth, cache));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

}
