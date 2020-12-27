package ru.veryprosto.restVote;

public class dsfsdf {
/*
@RequestMapping
public String carsPage(Model model) {
model.addAttribute("cars", service.getAll());
ModelHelper.addUserAuthModel(model,userAuthDto);
return "cars";
}
-----------------
@RequestMapping
public String mainPage(Model model) {
model.addAttribute("countCars", service.getCountCars());
model.addAttribute("countRepairs", service.getCountRepairs());
model.addAttribute("countRepairsComplete", service.getCountRepairsComplete());
model.addAttribute("countOwners", service.getCountOwners());
ModelHelper.addUserAuthModel(model,userAuthDto);
return "main";
}
------------------

    @PostMapping(value = "/car/save")
    public synchronized ModelAndView saveCar(@ModelAttribute("сar") Car car, Model model,
                                             RedirectAttributes redirectAttributes) throws InterruptedException {
        ModelAndView modelAndView = new ModelAndView();
        if (service.isExist(car)) {
            //выводим ту же форму с отображением ошибки
            model.addAttribute("errorMessage", "Автомобиль с таким номером уже существует!");
            model.addAttribute("title", "Добавление автомобиля");
            model.addAttribute("engineCapacities", DicHelper.getCapacity(1.0,5.0));
            model.addAttribute("engineTypes", engineTypes);
            model.addAttribute("yearBuildOut", DicHelper.getYearBuildOut(1995, LocalDate.now().getYear()));
            model.addAttribute("car", car);
            model.addAttribute("owners", ownerService.getAll(true));
            modelAndView.setViewName("addcar");
        }else {
            Integer clientId = car.getOwner().getId();

            //проверяем существование клиента
            Optional<Owner> client = Optional.ofNullable(ownerService.getById(clientId));
            if (!client.isPresent()) {
                //подставляем виртуального
                clientId = ownerService.save(ownerService.createVirtual());
            }

            Optional<Owner>  newOwner = Optional.ofNullable(ownerService.getById(clientId));
            if (newOwner.isPresent()) {
                car.setOwner(newOwner.get());
                service.save(car);
            }else{
                throw new RuntimeException("Не удалось определить владельца");
            }

            RedirectView redirectView = new RedirectView("../");
            redirectAttributes.addFlashAttribute("successMessage", "Успешно сохранено");
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            modelAndView.setView(redirectView);
            //return "redirect:../";
        }
        return modelAndView;
    }*/
}
