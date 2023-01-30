public static boolean isOfTheSameStructure(BinaryTree bt1, BinaryTree bt2) {
        boolean left = true;
        boolean right = true;
        if (bt1.getRight()!=null&&bt2.getRight()!=null) right = isOfTheSameStructure(bt1.getRight(), bt2.getRight());
        if (bt1.getLeft()!=null&&bt2.getLeft()!=null) left = isOfTheSameStructure(bt1.getLeft(), bt2.getLeft());
        if ((bt1.getRight()==null&&bt2.getRight()!=null)||(bt1.getRight()!=null&&bt2.getRight()==null)) right = false;
        if ((bt1.getLeft()==null&&bt2.getLeft()!=null)||(bt1.getLeft()!=null&&bt2.getLeft()==null)) left = false;
        return right&&left;
    }
