package hundeklemmen.legacy.extra;

/**
 * VersionField
 *
 * Reflection class that contains an object value
 * Allow to chain the call
 *
 * Vanilla Java
 * myObject.afield.anotherfield
 *
 * Reflection VersionField
 * VersionField.from(myObject).get("afield").get("anotherfield").value()
 */
class VersionField {

    /**
     * Field value
     */
    private Object object;

    /**
     * The constructor
     *
     * @param obj value to assign to object
     */
    VersionField(Object obj) {
        this.object = obj;
    }

    /**
     * Create classe instance
     */
    public static VersionField from(Object obj) {
        return new VersionField(obj);
    }

    /**
     * Reflection method to get a field value
     * Store the field value and return a new instance of VersionField
     *
     * @param name field name
     * @return a new instance of VersionField
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    VersionField get(String name) throws NoSuchFieldException, IllegalAccessException {
        Object ret = this.object.getClass().getField(name).get(this.object);
        return new VersionField(ret);
    }

    /**
     * Return the object value stored
     *
     * @return
     */
    Object value() {
        return this.object;
    }
}
