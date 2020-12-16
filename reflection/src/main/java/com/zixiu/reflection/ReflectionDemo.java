package com.zixiu.reflection;

/**
 * 实践出真知
 */
public class ReflectionDemo {

    //生产手机接口
    interface PhoneBrand{
        void phoneBrand();
    }

    //生产电脑接口
    interface ComputerBrand{
        void computerBrand();
    }

    /**
     * 小米手机生产类
     */
    static class MiPhone implements PhoneBrand{
        static String CLASS_NAME = "com.zixiu.reflection.ReflectionDemo$MiPhone";
        @Override
        public void phoneBrand() {
            System.out.println("我是富士康代生产的小米手机");
        }
    }

    /**
     * 华为手机生产类
     */
    static class HuaweiPhone implements PhoneBrand{
        static String CLASS_NAME = "com.zixiu.reflection.ReflectionDemo$HuaweiPhone";
        @Override
        public void phoneBrand() {
            System.out.println("我是富士康代生产的华为手机");
        }
    }

    static class MiComputer implements ComputerBrand{
        static String CLASS_NAME = "com.zixiu.reflection.ReflectionDemo$MiComputer";
        @Override
        public void computerBrand() {
            System.out.println("我是富士康代生产的小米电脑");
        }
    }

    /**
     * 工厂类
     */
    static class Factory{
        static <T> T getInstance(String className,Class<T> intefaceClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
            Object object = Class.forName(className).newInstance();
            //将各个产品类转换的各自的接口类型
            return intefaceClass.cast(object);
        }
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        System.out.println("-------测试类-------");
        PhoneBrand miPhone = Factory.getInstance(MiPhone.CLASS_NAME, PhoneBrand.class);
        miPhone.phoneBrand();
        PhoneBrand huaweiPhone = Factory.getInstance(HuaweiPhone.CLASS_NAME, PhoneBrand.class);
        huaweiPhone.phoneBrand();
        ComputerBrand computerBrand = Factory.getInstance(MiComputer.CLASS_NAME, ComputerBrand.class);
        computerBrand.computerBrand();
    }

}

