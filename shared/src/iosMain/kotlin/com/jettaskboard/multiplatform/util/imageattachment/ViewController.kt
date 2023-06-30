package com.jettaskboard.multiplatform.util.imageattachment;

import platform.Foundation.NSCoder
import platform.UIKit.UIImage
import platform.UIKit.UIImagePickerController
import platform.UIKit.UIImagePickerControllerDelegateProtocol
import platform.UIKit.UIImagePickerControllerOriginalImage
import platform.UIKit.UIImagePickerControllerSourceType
import platform.UIKit.UINavigationControllerDelegateProtocol
import platform.UIKit.UIViewController

class ViewController : UIViewController, UIImagePickerControllerDelegateProtocol, UINavigationControllerDelegateProtocol {

    @OverrideInit constructor(coder: NSCoder) : super(coder)

    private var pickedImage: UIImage? = null

    fun getPickedImage(): UIImage? {
        return pickedImage
    }

    override fun viewDidLoad() {
        super.viewDidLoad()
    }

    public fun chooseImage() {
        val imagePickerController = UIImagePickerController()
        imagePickerController.delegate = this
        imagePickerController.sourceType = UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypePhotoLibrary
        this.presentViewController(imagePickerController, animated = true, completion = null)
    }

    override fun imagePickerController(picker: UIImagePickerController, didFinishPickingMediaWithInfo: Map<Any?, *>) {
        val pickedImage = didFinishPickingMediaWithInfo.getValue(UIImagePickerControllerOriginalImage) as? UIImage
        if (pickedImage != null) {
            this.pickedImage = pickedImage
        }
        picker.dismissViewControllerAnimated(true, completion = null)
    }

    override fun imagePickerControllerDidCancel(picker: UIImagePickerController) {
        picker.dismissViewControllerAnimated(true, completion = null)
    }
}

